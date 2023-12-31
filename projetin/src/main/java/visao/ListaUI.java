package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.GaloDAO;
import modelo.Galos;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class ListaUI extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private static ListaUI instance;

    public static ListaUI getInstance() {
        return instance;
    }

    

    public ListaUI() {
    	instance = this;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                carregarDados();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 999, 706);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        table.setBounds(73, 109, 766, 485);
        contentPane.add(table);

        JLabel lblGalosCadastrados = new JLabel("Galos cadastrados:");
        lblGalosCadastrados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblGalosCadastrados.setBounds(73, 34, 347, 36);
        contentPane.add(lblGalosCadastrados);

        JButton btnNewButton = new JButton("Editar galo");
        btnNewButton.setBackground(new Color(192, 192, 192));
        btnNewButton.setBounds(619, 34, 193, 34);
        contentPane.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um galo para editar.");
                    return;
                }

                int selectedGaloID = (int) table.getValueAt(selectedRow, 0);

                Galos galoParaEditar = buscarGaloPorID(selectedGaloID);

                if (galoParaEditar != null) {
                    EditeUI editeUI = new EditeUI(galoParaEditar);
                    editeUI.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível encontrar o galo com o ID selecionado.");
                }
            }

            private Galos buscarGaloPorID(int id) {
                GaloDAO dao = new GaloDAO();
                Galos galo = new Galos();
                galo.setIdGalo(id);
                return dao.buscarPorID(galo);
            }

        });
    }

    public void carregarDados() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Raça");
        model.addColumn("Poder");
        model.addColumn("Vida");

        GaloDAO dao = new GaloDAO();
        ArrayList<Galos> galos = dao.listar();

        for (Galos galo : galos) {
            Object[] rowData = { galo.getIdGalo(), galo.getName(), galo.getRaca(), galo.getPower(), galo.getLife() };
            model.addRow(rowData);
        }

        table.setModel(model);
    }
    
}
