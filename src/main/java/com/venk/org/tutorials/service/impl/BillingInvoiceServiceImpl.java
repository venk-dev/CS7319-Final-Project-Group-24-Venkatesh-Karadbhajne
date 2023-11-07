package com.venk.org.tutorials.service.impl;

import com.venk.org.tutorials.model.BillingInvoice;
import com.venk.org.tutorials.model.User;
import com.venk.org.tutorials.repository.BillingInvoiceRepository;
import com.venk.org.tutorials.service.BillingInvoiceService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author venkateshkaradbhajne
 */
@Service
public class BillingInvoiceServiceImpl implements BillingInvoiceService {

  @Autowired
  BillingInvoiceRepository billingInvoiceRepository;

  public List<BillingInvoice> getBillingInvoicesByUserId(User user){
    return billingInvoiceRepository.findBillingInvoiceByUser(user);
  }

  public BillingInvoice getBillingInvoicesById(long id) {
    return billingInvoiceRepository.findById(id).orElse(null);
  }

  public BillingInvoice addBillingInvoice(BillingInvoice billingInvoice){
    return billingInvoiceRepository.save(billingInvoice);
  }
}
