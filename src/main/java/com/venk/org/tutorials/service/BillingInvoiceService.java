package com.venk.org.tutorials.service;

import com.venk.org.tutorials.model.BillingInvoice;
import com.venk.org.tutorials.model.User;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author venkateshkaradbhajne
 */
@Component
public interface BillingInvoiceService {

  public List<BillingInvoice> getBillingInvoicesByUserId(User user);

  public BillingInvoice getBillingInvoicesById(long id);

  public BillingInvoice addBillingInvoice(BillingInvoice billingInvoice);
}
