package hust.soict.hedspi.aims.aim;
import java.awt.*;       // Using AWT layouts

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;    // Using Swing components and containers

import hust.soict.hedspi.aims.disc.compactDisc;
import hust.soict.hedspi.aims.media.Track;

public class AimsGuiAddTrack extends JFrame{
private static final long serialVersionUID = 1L;
    
    private int trackInputted = 0;
    
    private JTextField txtName;
    private JTextField txtLength;
    private JLabel lblWarning;
    
    @SuppressWarnings("unused")
    private JLabel lblNumOfTrack;
    
    public AimsGuiAddTrack(compactDisc newCD, int numOfTrack) {
        JFrame thisFrame = this;
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Add Track To CD");
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{84, 259, 49, 0};
        gridBagLayout.rowHeights = new int[]{41, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JPanel pnlInfo = new JPanel();
        GridBagConstraints gbc_pnlInfo = new GridBagConstraints();
        gbc_pnlInfo.anchor = GridBagConstraints.SOUTHWEST;
        gbc_pnlInfo.insets = new Insets(0, 0, 5, 5);
        gbc_pnlInfo.gridx = 1;
        gbc_pnlInfo.gridy = 0;
        getContentPane().add(pnlInfo, gbc_pnlInfo);
        pnlInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblInfo = new JLabel("Enter information of track:");
        pnlInfo.add(lblInfo);
        
        JLabel lblNumOfTrack = new JLabel("0/" + numOfTrack);
        pnlInfo.add(lblNumOfTrack);
        
        JLabel lbltxtName = new JLabel("  Track's txtName:");
        GridBagConstraints gbc_lbltxtName = new GridBagConstraints();
        gbc_lbltxtName.insets = new Insets(0, 0, 5, 5);
        gbc_lbltxtName.anchor = GridBagConstraints.EAST;
        gbc_lbltxtName.gridx = 0;
        gbc_lbltxtName.gridy = 1;
        getContentPane().add(lbltxtName, gbc_lbltxtName);
        
        txtName = new JTextField();
        GridBagConstraints gbc_txtName = new GridBagConstraints();
        gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtName.insets = new Insets(0, 0, 5, 5);
        gbc_txtName.gridx = 1;
        gbc_txtName.gridy = 1;
        getContentPane().add(txtName, gbc_txtName);
        txtName.setColumns(10);
        
        JPanel blankPanel1 = new JPanel();
        GridBagConstraints gbc_blankPanel1 = new GridBagConstraints();
        gbc_blankPanel1.insets = new Insets(0, 0, 5, 0);
        gbc_blankPanel1.gridheight = 4;
        gbc_blankPanel1.anchor = GridBagConstraints.NORTHWEST;
        gbc_blankPanel1.gridx = 2;
        gbc_blankPanel1.gridy = 1;
        getContentPane().add(blankPanel1, gbc_blankPanel1);
        
        JLabel lbltxtLength = new JLabel("    Track's txtLength:");
        GridBagConstraints gbc_lbltxtLength = new GridBagConstraints();
        gbc_lbltxtLength.anchor = GridBagConstraints.EAST;
        gbc_lbltxtLength.insets = new Insets(0, 0, 5, 5);
        gbc_lbltxtLength.gridx = 0;
        gbc_lbltxtLength.gridy = 2;
        getContentPane().add(lbltxtLength, gbc_lbltxtLength);
        
        txtLength = new JTextField();
        GridBagConstraints gbc_txtLength = new GridBagConstraints();
        gbc_txtLength.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtLength.insets = new Insets(0, 0, 5, 5);
        gbc_txtLength.gridx = 1;
        gbc_txtLength.gridy = 2;
        getContentPane().add(txtLength, gbc_txtLength);
        txtLength.setColumns(10);
        
        lblWarning = new JLabel("Please complete all fields");
        GridBagConstraints gbc_lblWarning = new GridBagConstraints();
        gbc_lblWarning.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarning.gridx = 1;
        gbc_lblWarning.gridy = 3;
        getContentPane().add(lblWarning, gbc_lblWarning);
        lblWarning.setVisible(false);
        
        JPanel pnlBtn = new JPanel();
        GridBagConstraints gbc_pnlBtn = new GridBagConstraints();
        gbc_pnlBtn.fill = GridBagConstraints.BOTH;
        gbc_pnlBtn.insets = new Insets(0, 0, 5, 5);
        gbc_pnlBtn.gridx = 1;
        gbc_pnlBtn.gridy = 4;
        getContentPane().add(pnlBtn, gbc_pnlBtn);
        pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnAdd = new JButton("Add");
        pnlBtn.add(btnAdd);
        
        JPanel blankPanel2 = new JPanel();
        pnlBtn.add(blankPanel2);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisFrame.dispose();
            }
        });
        pnlBtn.add(btnCancel);
        
        // add listener
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // check all field filled
                if (txtName.getText().isBlank() || txtLength.getText().isBlank()) {
                    warning(true, "Please complete all fields first!");
                    return;
                }
                // check info -> valid -> add
                Track newTrack = new Track(txtName.getText(), Integer.parseInt(txtLength.getText()));
                if (newCD.searchTrack(newTrack) != -1) {
                    warning(true, "Track already exists!");
                    return;
                } else {
                    newCD.addTrack(newTrack);
                }
                // increase the num
                trackInputted++;
                lblNumOfTrack.setText(trackInputted + "/" + numOfTrack);
                // check the num -> -> complete
                if (trackInputted == numOfTrack) {
                    // inform: completed -> btn: OK
                    lblInfo.setText("Inputting track(s) completed:");
                    lbltxtName.setVisible(false);
                    txtName.setVisible(false);
                    lbltxtLength.setVisible(false);
                    txtLength.setVisible(false);
                    btnAdd.setVisible(false);
                    blankPanel2.setVisible(false);
                    lblWarning.setVisible(false);
                    btnCancel.setText("OK");
                }
                // else -> clear the text fields -> keep on inputting
                txtName.setText("");
                txtLength.setText("");
                warning(false, "");
            }
        });
        
        this.setBounds(325, 175, 393, 200);
        this.setVisible(true);
    }
    
    private void warning(boolean isVisible, String warningText) {
        lblWarning.setText(warningText);
        lblWarning.setVisible(isVisible);
    }
}