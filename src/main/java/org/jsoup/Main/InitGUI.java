package org.jsoup.Main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by akeske
 */
public class InitGUI extends JFrame implements ActionListener {
    Font font = new Font("Verdana", Font.BOLD,11);

    Task task;

    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem menuSettings;
    private JMenuItem close;

    private Container container;
    private JPanel panelMain;
    private JPanel panelStatus;
    private JPanel panelTable;
    private JPanel panelSettings;

    private JLabel labelStatus;
    private JTextField txtColorStatus;

    private JLabel labelDepth;
    private JTextField txtDepth;
    private JLabel labelURL;
    private JTextField txtURL;
    private static JTable tableUsers;
    public static JButton btnRun;

    private Border border;
    private Insets insets;

    private JProgressBar progressBar;

    public InitGUI() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });

        insets = new Insets(5, 5, 10, 10);
        container = getContentPane();
        container.setLayout(new BorderLayout());

        setLookAndFeel();
        SwingUtilities.updateComponentTreeUI(this);

        panelMain = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;
        panelMain.add(addPanelSettigns(), gbc);
        gbc.gridy = 1;
        panelMain.add(addPanelTable(), gbc);

        container.add(addPanelStatus(), BorderLayout.SOUTH);
        container.add(panelMain, BorderLayout.CENTER);

        setJMenuBar(generateMenu());

        pack();
   //     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ico.png")));
        setBounds(20, 20, 20, 20);
        setResizable(false);
        setSize(920, 550);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Component addPanelSettigns(){
        labelURL = new JLabel("Insert the url that will scan first.");
        txtURL = new JTextField(Main.FIRSTURL, 150);

        labelDepth = new JLabel("Insert the maximum depth that you want to scan (from 1 to 3 or 4).");
        txtDepth = new JTextField("1", 20);

        panelSettings = new JPanel(true);
        panelSettings.setFont(font);
        panelSettings.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Settings"));
        panelSettings.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
     //   gbc.insets = insets;
        panelSettings.add(labelURL, gbc);

        gbc.gridx = 1;
        panelSettings.add(txtURL, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        panelSettings.add(labelDepth, gbc);

        gbc.gridx = 1;
        panelSettings.add(txtDepth, gbc);

        return panelSettings;
    }

    private Component addPanelTable() {
        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;

        panelTable = new JPanel(true);
        panelTable.setFont(font);
    //    panelTable.setPreferredSize(new Dimension(400, 400));
        panelTable.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "User's information"));
        panelTable.setLayout(new GridBagLayout());

        tableUsers = new JTable(new MyTableModel());
    //    tableUsers.setPreferredScrollableViewportSize(new Dimension(700, 850));
    //    tableUsers.setMinimumSize(new Dimension(600,200));
    //    tableUsers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    //    tableUsers.setFillsViewportHeight(true);
        tableUsers.setAutoCreateRowSorter(true);
        tableUsers.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(tableUsers, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMinimumSize(new Dimension(550, 250));
        scrollPane.getHorizontalScrollBar();
        panelTable.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;

        btnRun = new JButton("Start");
        btnRun.setActionCommand("start");
        btnRun.addActionListener(this);
        panelTable.add(btnRun, gbc);

        return panelTable;
    }

    public static void setLookAndFeel(){
        String metalClassName = "javax.swing.plaf.metal.MetalLookAndFeel";
        String motifClassName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        String windowsClassName  = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        String OS = null;

        try{
            if(OS == null) { OS = System.getProperty("os.name"); }

            if(OS.startsWith("Windows")){
                UIManager.setLookAndFeel(windowsClassName);
            }else{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private JPanel addPanelStatus(){
        panelStatus = new JPanel(true);
        panelStatus.setLayout(new GridBagLayout());

        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = insets;

        gbc.gridx = 0;
        txtColorStatus = new JTextField(1);
        txtColorStatus.setBorder(border);
        txtColorStatus.setEnabled(false);
    //    panelStatus.add(txtColorStatus, gbc);

        gbc.gridx = 1;
        labelStatus = new JLabel();
        labelStatus.setFont(font);
        labelStatus.setPreferredSize(new Dimension(400, 20));
        labelStatus.setHorizontalAlignment(SwingConstants.LEFT);
        panelStatus.add(labelStatus, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        int min = 0;
        int max = 100;
        progressBar = new JProgressBar(min, max);
        progressBar.setVisible(false);
        progressBar.setStringPainted(true);
        panelStatus.add(progressBar, gbc);

        return panelStatus;
    }

    private JMenuBar generateMenu(){

        menubar = new JMenuBar();
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_F);
        menubar.add(menu);

        menuSettings = new JMenuItem("Settings");
        menuSettings.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        menuSettings.addActionListener(new MenuListener(this));
    //    menu.add(menuSettings);
        menu.addSeparator();

        close = new JMenuItem("Exit");
        close.addActionListener(new MenuListener(this));
        close.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
        menu.add(close);
        return menubar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int maxDepth;
        try {
            maxDepth = Integer.valueOf(txtDepth.getText());
        }catch (NumberFormatException e2) {
            maxDepth = 1;
        }
        System.out.println("The maximum depth is " + maxDepth);
        btnRun.setEnabled(false);
        labelStatus.setText("");
        Main.newUsersCounter = 0;
        Main.duplicateUsersCounter = 0;
        Main.users.clear();
        progressBar.setVisible(true);
        txtDepth.setEnabled(false);
        txtURL.setEnabled(false);
        Task task = new Task(progressBar, txtDepth, txtURL, labelStatus);
        task.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if( e.getPropertyName().equals("progress") ) {
                    progressBar.setValue((Integer) e.getNewValue());
                }
            }
        });
        task.execute();
    }

    private class MenuListener extends Component implements ActionListener {

        private InitGUI mainFrame;

        public MenuListener(InitGUI initGUI) {
            mainFrame = initGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==menuSettings){
            }else if(e.getSource()==close){
                //    System.out.println("selected "+e.getActionCommand());
                System.exit(0);
            }else{
                System.out.println("wrong input");
            }
        }
    }

    class MyTableModel extends AbstractTableModel {

        private String[] columnNames ={"id","username","full_name","description","country",
                "city","track_count","public_favorites_count","followers_count","followings_count",
                "likes_count","comments_count"};

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public int getRowCount() {
            return Main.getUsers().size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            UserInfo user = Main.getUsers().get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return user.getId();
                case 1:
                    return user.getUsername();
                case 2:
                    return user.getFull_name();
                case 3:
                    return user.getDescription();
                case 4:
                    return user.getCountry();
                case 5:
                    return user.getCity();
                case 6:
                    return user.getTrack_count();
                case 7:
                    return user.getPublic_favorites_count();
                case 8:
                    return user.getFollowers_count();
                case 9:
                    return user.getFollowings_count();
                case 10:
                    return user.getLikes_count();
                case 11:
                    return user.getComments_count();
                default:
                    return null;
            }
        }

        @Override
        public Class getColumnClass(int column) {
            for (int row = 0; row < getRowCount(); row++) {
                Object o = getValueAt(row, column);
                if (o != null) {
                    return o.getClass();
                }
            }
            return Object.class;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
          //  if (col < 2) {
          //      return false;
          //  } else {
          //      return true;
          //  }
            return false;
        }
    }

    public static void repaintTable(){
        tableUsers.updateUI();
    }
}