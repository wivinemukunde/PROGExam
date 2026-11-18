import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ProductApp extends JFrame {

    private JTextField txtCustomerName;
    private JTextField txtPrice;
    private JComboBox<String> cmbProduct;

    public ProductApp() {

        setTitle("Product App");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

// Menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        fileMenu.add(exitItem);

        editMenu.add(processItem);
        editMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

// Form panel
        JPanel panel = new JPanel(new BorderLayout(10, 15));

        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 15));

// Customer name
        inputPanel.add(new JLabel("Customer Name:"));

        txtCustomerName = new JTextField();
        inputPanel.add(txtCustomerName);

// Product selection
        inputPanel.add(new JLabel("Select Product:"));

        String[] products = {
                "Cozy Nights Monarch",
                "Restonic Barbados",
                "Sealy Phuket"
        };

        cmbProduct = new JComboBox<>(products);
        inputPanel.add(cmbProduct);

// Product price
        inputPanel.add(new JLabel("Enter Price:"));

        txtPrice = new JTextField();
        inputPanel.add(txtPrice);

// Process button
        JButton btnProcess = new JButton("PROCESS");

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(btnProcess, BorderLayout.SOUTH);

        add(panel);

// Button actions
        btnProcess.addActionListener(e -> ProcessSale());

// Menu actions
        processItem.addActionListener(e -> ProcessSale());

        clearItem.addActionListener(e -> ClearFields());

        exitItem.addActionListener(e -> System.exit(0));
    }

    public void ProcessSale() {

        String customerName = txtCustomerName.getText().trim();

        String productName = (String) cmbProduct.getSelectedItem();

        double price;

        if (customerName.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please enter the customer name.");

            return;
        }

        try {

            price = Double.parseDouble(txtPrice.getText());

            if (price <= 0) {

                JOptionPane.showMessageDialog(this,
                        "Please enter a valid price.");

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Please enter a valid price.");

            return;
        }

        ProductCalculation calculations =
                new ProductCalculation(price);

        double vat = calculations.GetVat();

        double commission = calculations.GetCommission();

        double total = calculations.GetTotal();

// Save information to data.txt
        try {

            PrintWriter writer = new PrintWriter(
                    new FileWriter("data.txt", true));

            writer.println("DATA LOG");
            writer.println("************************");
            writer.println("Customer Name: " + customerName);
            writer.println("Product: " + productName);
            writer.println("VAT: R " + vat);
            writer.println("COMMISSION: R " + commission);
            writer.println("TOTAL: R " + total);
            writer.println("************************");

            writer.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this,
                    "Error saving information.");

            return;
        }

// Display results
        JOptionPane.showMessageDialog(this,
                "Customer Name: " + customerName
                        + "\nProduct: " + productName
                        + "\nVAT: R " + vat
                        + "\nCOMMISSION: R " + commission
                        + "\nTOTAL: R " + total);
    }

    public void ClearFields() {

        txtCustomerName.setText("");

        txtPrice.setText("");

        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ProductApp app = new ProductApp();

            app.setVisible(true);
        });
    }
}