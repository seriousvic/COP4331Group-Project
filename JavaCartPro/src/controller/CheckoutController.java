package JavaCartPro.src.controller;
import JavaCartPro.src.model.*;
import JavaCartPro.src.ui.*;

import javax.swing.*;
import java.util.List;

public class CheckoutController {
    public void performCheckout(AppData appData, List<ProductInterface> cartItems, Customer customer, CheckoutView checkoutView) {
        for (ProductInterface item : cartItems) {
            // Retrieve the seller from each product
            Seller seller = item.getSellerAccount();

            // Create a separate transaction for each product
            Transaction transaction = new Transaction(customer, seller, List.of(item));

            // Add the transaction to the seller's financial history
            seller.getFinancialHistory().addTransaction(transaction);
        }

        // Clear the customer's shopping cart after checkout
        customer.getShoppingCart().clearCart();

        // Display a confirmation message
        JOptionPane.showMessageDialog(checkoutView, "Checkout successful!");
        DataManager.saveData(appData);


        // Close the checkout screen
        checkoutView.dispose();
    }
}
