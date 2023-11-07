package com.venk.org.tutorials.repository;

import com.venk.org.tutorials.model.BillingInvoice;
import com.venk.org.tutorials.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author venkateshkaradbhajne
 */
public interface BillingInvoiceRepository extends JpaRepository<BillingInvoice, Long> {
  List<BillingInvoice> findBillingInvoiceByUser(User user);
}
