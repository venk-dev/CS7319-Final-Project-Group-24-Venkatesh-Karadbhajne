package com.venk.org.tutorials.controller;

import com.venk.org.tutorials.model.BillingInvoice;
import com.venk.org.tutorials.model.BillingInvoicePayment;
import com.venk.org.tutorials.model.User;
import com.venk.org.tutorials.service.BillingInvoiceService;
import com.venk.org.tutorials.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venkateshkaradbhajne
 */
@EnableMethodSecurity
@RestController
@RequestMapping("/api/billinginvoices")
public class BillingInvoiceController {

  @Autowired
  BillingInvoiceService billingInvoiceService;
  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<List<BillingInvoice>> getAllBillingInvoices(@RequestParam(required = false) String title, HttpServletRequest request) {
    try {
      List<BillingInvoice> billingInvoices = new ArrayList<BillingInvoice>();
      String userName = request.getUserPrincipal().getName();
      User user = userService.getUserByName(userName);
      billingInvoices = billingInvoiceService.getBillingInvoicesByUserId(user);

      if (billingInvoices.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(billingInvoices, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping("/{billingInvoiceId}")
  public ResponseEntity<BillingInvoice> payBillingInvoice(@PathVariable("billingInvoiceId") long billingInvoiceId, @RequestBody
      BillingInvoicePayment billingInvoicePayment, HttpServletRequest request) {
    try {
      BillingInvoice billingInvoice = billingInvoiceService.getBillingInvoicesById(billingInvoiceId);
      String userName = request.getUserPrincipal().getName();
      User user = userService.getUserByName(userName);
      if (billingInvoice != null)
      {
        billingInvoice.setUser(user);
        billingInvoice.setStatus(BillingInvoice.StatusEnum.paid);
        billingInvoice.setPaymentConfirmationId(billingInvoicePayment.getPaymentConfirmationId());
        billingInvoice.setTotalAmount(billingInvoicePayment.getTotalAmount());
      }

      return new ResponseEntity<>(billingInvoiceService.addBillingInvoice(billingInvoice), HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
