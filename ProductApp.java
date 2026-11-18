
package productapp;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {

    private static final String DATA_FILE = "data.txt";

    private final JTextField txtName = new JTextField(20);
    private final JComboBox<String> cmbProduct = new JComboBox<>(new String[]{
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private final JTextField txtPrice = new JTextField(20);
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildMenu();
        buildForm();
        pack();
        setSize(500, 250);
        setLocationRelativeTo(null);
    }

    private void buildMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);

        JMenu tools = new JMenu("Tools");
        JMenuItem process = new JMenuItem("Process");
        process.addActionListener(e -> processSale());
        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(e -> clearForm());
        tools.add(process);
        tools.add(clear);

        bar.add(file);
        bar.add(tools);
        setJMenuBar(bar);
    }

    private void buildForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; panel.add(new JLabel("Customer Name:"), c);
        c.gridx = 1;              panel.add(txtName, c);
        c.gridx = 0; c.gridy = 1; panel.add(new JLabel("Select Product:"), c);
        c.gridx = 1;              panel.add(cmbProduct, c);
        c.gridx = 0; c.gridy = 2; panel.add(new JLabel("Enter Price:"), c);
        c.gridx = 1;              panel.add(txtPrice, c);
        c.gridx = 1; c.gridy = 3; panel.add(btnProcess, c);

        btnProcess.addActionListener(e -> processSale());
        add(panel);
    }

    private void processSale() {
        String name = txtName.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer's name.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
            if (price < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price (e.g. 3500).",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        IProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        String message = String.format(
                "Customer Name: %s%nProduct: %s%nVAT: R %.1f%nCOMMISSION: R %.1f%nTOTAL: R %.1f",
                name, product, vat, commission, total);

        saveToFile(name, product, vat, commission, total);
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveToFile(String name, String product, double vat, double commission, double total) {
        boolean newFile = !new File(DATA_FILE).exists();
        try (PrintWriter out = new PrintWriter(new FileWriter(DATA_FILE, true))) {
            if (newFile) {
                out.println("DATA LOG");
            }
            out.println("*******************************");
            out.printf("Customer Name: %s%n", name);
            out.printf("Product: %s%n", product);
            out.printf("VAT: R %.1f%n", vat);
            out.printf("COMMISSION: R %.1f%n", commission);
            out.printf("TOTAL: R %.1f%n", total);
            out.println("*******************************");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save to " + DATA_FILE + ": " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}