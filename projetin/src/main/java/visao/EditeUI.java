package visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.galosDAO;
import modelo.Galos;

public class EditeUI extends JFrame {

    private JPanel contentPane;
    private JTextField idTextField;
    private JTextField racaTextField;
    private JTextField nameTextField;
    private JTextField powerTextField;
    private JTextField lifeTextField;
    private int galoID;
    private ListaUI listaUI;

   

    public EditeUI(int galoID) {
        this.galoID = galoID; 
        initComponents(); 
    }

    private void initComponents() {
    	 this.galoID = galoID;
    	    this.listaUI = listaUI; 
    	    initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1193, 724);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        idLabel.setBounds(136, 109, 213, 36);
        JLabel racaLabel = new JLabel("Raça:");
        racaLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        racaLabel.setBounds(136, 270, 213, 36);
        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        nameLabel.setBounds(136, 190, 213, 36);
        JLabel powerLabel = new JLabel("Poder de Combate:");
        powerLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        powerLabel.setBounds(136, 350, 213, 36);
        JLabel lifeLabel = new JLabel("Vida:");
        lifeLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lifeLabel.setBounds(136, 425, 157, 36);

        idTextField = new JTextField(10);
        idTextField.setBackground(new Color(192, 192, 192));
        idTextField.setBounds(405, 114, 441, 36);
        racaTextField = new JTextField(10);
        racaTextField.setBackground(new Color(192, 192, 192));
        racaTextField.setBounds(405, 195, 441, 36);
        nameTextField = new JTextField(10);
        nameTextField.setBackground(new Color(192, 192, 192));
        nameTextField.setBounds(405, 275, 441, 36);
        powerTextField = new JTextField(10);
        powerTextField.setBackground(new Color(192, 192, 192));
        powerTextField.setBounds(405, 350, 441, 36);
        lifeTextField = new JTextField(10);
        lifeTextField.setBackground(new Color(192, 192, 192));
        lifeTextField.setBounds(405, 430, 441, 36);

        JButton updateButton = new JButton("Atualizar galo");
        updateButton.setBackground(new Color(0, 128, 128));
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        updateButton.setBounds(217, 558, 245, 53);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idTextField.getText().isEmpty() || racaTextField.getText().isEmpty() ||
                    nameTextField.getText().isEmpty() || powerTextField.getText().isEmpty() ||
                    lifeTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Certifique-se de preencher todos os campos.");
                    return;
                }

                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String raca = racaTextField.getText();
                    String nome = nameTextField.getText();
                    int poder = Integer.parseInt(powerTextField.getText());
                    int vida = Integer.parseInt(lifeTextField.getText());

                    Galos galo = new Galos();
                    galo.setIdGalo(id);
                    galo.setRaca(raca);
                    galo.setName(nome);
                    galo.setPower(poder);
                    galo.setLife(vida);

                    galosDAO dao = new galosDAO();
                    boolean atualizadoComSucesso = dao.atualizar(galo);

                    if (atualizadoComSucesso) {
                        JOptionPane.showMessageDialog(null, "Galo atualizado com sucesso!");
                        
                        ListaUI.getInstance().carregarDados();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar o galo.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Certifique-se de que os campos numéricos estão preenchidos corretamente.");
                    ex.printStackTrace();
                }
            }
        });

        JButton deleteButton = new JButton("Excluir galo");
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 17));
        deleteButton.setBackground(new Color(255, 128, 128));
        deleteButton.setBounds(523, 558, 245, 53);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Informe o ID do galo a ser excluído.");
                    return;
                }

                try {
                    int id = Integer.parseInt(idTextField.getText());
                    Galos galo = new Galos();
                    galo.setIdGalo(id);

                    galosDAO dao = new galosDAO();
                    boolean excluidoComSucesso = dao.excluir(galo);
                    if (excluidoComSucesso) {
                        JOptionPane.showMessageDialog(null, "Galo excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir o galo.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Certifique-se de que o ID é um número válido.");
                    ex.printStackTrace();
                }
            }
        }
    );

    contentPane.setLayout(null);

    contentPane.add(idLabel);
    contentPane.add(idTextField);
    contentPane.add(racaLabel);
    contentPane.add(racaTextField);
    contentPane.add(nameLabel);
    contentPane.add(nameTextField);
    contentPane.add(powerLabel);
    contentPane.add(powerTextField);
    contentPane.add(lifeLabel);
    contentPane.add(lifeTextField);
    contentPane.add(updateButton);
    contentPane.add(deleteButton);

    JLabel lblAdicioneSeuGalo = new JLabel("ATUALIZE/EXCLUA SEU ");
    lblAdicioneSeuGalo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
    lblAdicioneSeuGalo.setBounds(359, 29, 297, 36);
    contentPane.add(lblAdicioneSeuGalo);

    JLabel lblGaloDeGuerra = new JLabel("GALO DE GUERRA!!!!");
    lblGaloDeGuerra.setForeground(new Color(255, 0, 0));
    lblGaloDeGuerra.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
    lblGaloDeGuerra.setBounds(607, 29, 463, 36);
    contentPane.add(lblGaloDeGuerra);

    JLabel lblGaloDeGuerra_1 = new JLabel("*");
    lblGaloDeGuerra_1.setForeground(Color.RED);
    lblGaloDeGuerra_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
    lblGaloDeGuerra_1.setBounds(193, 109, 31, 36);
    contentPane.add(lblGaloDeGuerra_1);

    JLabel lblGaloDeGuerra_1_1 = new JLabel("*");
    lblGaloDeGuerra_1_1.setForeground(Color.RED);
    lblGaloDeGuerra_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
    lblGaloDeGuerra_1_1.setBounds(217, 195, 31, 36);
    contentPane.add(lblGaloDeGuerra_1_1);

    JLabel lblGaloDeGuerra_1_2 = new JLabel("*");
    lblGaloDeGuerra_1_2.setForeground(Color.RED);
    lblGaloDeGuerra_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
    lblGaloDeGuerra_1_2.setBounds(206, 270, 31, 36);
    contentPane.add(lblGaloDeGuerra_1_2);

    JLabel lblGaloDeGuerra_1_3 = new JLabel("*");
    lblGaloDeGuerra_1_3.setForeground(Color.RED);
    lblGaloDeGuerra_1_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
    lblGaloDeGuerra_1_3.setBounds(337, 350, 31, 36);
    contentPane.add(lblGaloDeGuerra_1_3);

    JLabel lblGaloDeGuerra_1_4 = new JLabel("!!");
    lblGaloDeGuerra_1_4.setForeground(Color.RED);
    lblGaloDeGuerra_1_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
    lblGaloDeGuerra_1_4.setBounds(497, 566, 31, 36);
    contentPane.add(lblGaloDeGuerra_1_4);
}
}