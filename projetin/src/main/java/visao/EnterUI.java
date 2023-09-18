package visao;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterUI extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EnterUI frame = new EnterUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EnterUI() {
        setResizable(false);
        setBackground(new Color(128, 128, 128));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1194, 725);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblBemVindo = new JLabel("Bem Vindo:)");
        lblBemVindo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 49));
        lblBemVindo.setBounds(379, 72, 365, 206);
        contentPane.add(lblBemVindo);

        JButton btnAdicionarGalo = new JButton("Adicione seu galo");
        btnAdicionarGalo.setBackground(new Color(0, 255, 128));
        btnAdicionarGalo.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnAdicionarGalo.setBounds(230, 346, 207, 110);
        contentPane.add(btnAdicionarGalo);

        JButton btnVerListaDeGalos = new JButton("Ver lista de galos");
        btnVerListaDeGalos.setBackground(new Color(128, 128, 192));
        btnVerListaDeGalos.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnVerListaDeGalos.setBounds(612, 346, 207, 110);
        contentPane.add(btnVerListaDeGalos);

        
        btnAdicionarGalo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GaloGUI galoGUI = new GaloGUI();
                galoGUI.setVisible(true);
                dispose(); 
            }
        });

        
        btnVerListaDeGalos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListaUI listeUI = new ListaUI();
                listeUI.setVisible(true);
                dispose(); 
            }
        });
    }
}
