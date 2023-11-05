/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.Implementation.FuncionarioImpl;
import DAO.Implementation.GrupoFamiliarImpl;
import DAO.Implementation.InformacionAcademicaImpl;
import DAO.Interface.FuncionarioDao;
import DAO.Interface.GrupoFamiliarDao;
import DAO.Interface.InformacionAcademicaDao;
import DAO.ValueObject.Funcionario;
import DAO.ValueObject.GrupoFamiliar;
import DAO.ValueObject.InformacionAcademica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jenny_izquierdo
 */
public class FrmCrudFuncionarios extends javax.swing.JFrame {

    /**
     * Creates new form FrmCrudFuncionarios
     */
    public FrmCrudFuncionarios() {
        initComponents();
        
        // Clear Components
        clearComponents();
        
        // Assing title
        jlblAppName.setText("App Funcionarios");
        
        formatDate();
        
        // Set Timer
        setTimer();
        
        // Load Data
        loadTables();
        
        
    }
    
    
    private void formatDate()
    {
        String pattern = "dd MMMM yyyy zzzz";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("es", "ES"));
        String date = simpleDateFormat.format(new Date());
        String[] arrOfDate = date.split(" ", 4);     
        String day = arrOfDate[0].substring(0, 1).toUpperCase() + arrOfDate[0].substring(1);
        String month =  arrOfDate[1].substring(0, 1).toUpperCase() + arrOfDate[1].substring(1);
        String year = arrOfDate[2];
        String local = arrOfDate[3];
        
        //30 mayo 2023 Hora de Colombia
        jlbFechaCompleta.setText(day + " de " + month + " del " + year);
        jlblPais.setText(local);
    }
    
    private void clearComponents()
    {
        txtSearchEmpleado.setText("");
        jlblAppName.setText("");
        jlblFecha.setText("");
        jlbFechaCompleta.setText("");
        jlbFechaCompleta.setText("");
        jlblPais.setText("");
        btnEditar.setFocusable(false);
    }
    
    // Create a Timer to update the time every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the current time
                Date currentTime = new Date();

                // Create a SimpleDateFormat to format the time
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");

                // Set the formatted time on the JLabel
                jlblFecha.setText(timeFormat.format(currentTime));
            }
     });
    
    private void setTimer()
    {
        timer.start();
    }
    
    private void loadTables()
    {
        // LoadDataFuncionarios
        loadJTableFuncionarios();
        //LoadDataGrupoFamiliar
        loadJTableGrupoFamiliar();
        //LoadDataInformacionAcademica
        loadJTableInformacionAcademica();
    }
     private void loadJTableInformacionAcademica()
    {
       // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        // Set the model to the table
        jTableInformacionAcademica.setModel(model);
        
        // Add columns to the model
        
        model.addColumn("No.Doc Funcionario");
        model.addColumn("Universidad");
        model.addColumn("Titulo Académico");
        
        // Call DAO Funcionarios
        InformacionAcademicaDao gInfoAcademica = new InformacionAcademicaImpl();
        
        // Add data to the model
         for(InformacionAcademica _gInfo : gInfoAcademica.getAllInformacionAcademica())
        {
            Object [] fetch = { _gInfo.getNumeroIdentificacionFuncionario(),
                                _gInfo.getUniversidad(),
                                _gInfo.getTituloEstudio()
            };
                
            model.addRow(fetch);
        }
         
         // Set auto resize mode to adjust column widths
        jTableInformacionAcademica.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
    
    
    private void loadJTableGrupoFamiliar()
    {
       // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        // Set the model to the table
        jTableGrupoFamiliar.setModel(model);
        
        // Add columns to the model
        model.addColumn("No.Doc Funcionario");
        model.addColumn("Tipo Identificacion");
        model.addColumn("Numero Identificacion");
        model.addColumn("Nombre Completo");
        model.addColumn("Telefono");
        model.addColumn("Rol");
        
        
        // Call DAO Funcionarios
        GrupoFamiliarDao gFamiliarDao = new GrupoFamiliarImpl();
        
        // Add data to the model
         for(GrupoFamiliar _gFamiliar : gFamiliarDao.getAllGrupoFamiliar())
        {
            Object [] fetch = { _gFamiliar.getNumeroIdentificacionFuncionario(),
                                _gFamiliar.getTipoIdentificacion(),
                                _gFamiliar.getNumeroIdentificacion(),
                                _gFamiliar.getNombreCompleto(),
                                _gFamiliar.getTelefono(),
                               _gFamiliar.getRol()
            };
                
            model.addRow(fetch);
        }
         
         // Set auto resize mode to adjust column widths
        jTableGrupoFamiliar.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
   
   
private void loadJTableFuncionarios()
    {    
        // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        
        // Set the model to the table
        jTableFuncionarios.setModel(model);
        
        // Add columns to the model
        
        model.addColumn("Id");
        model.addColumn("Tipo Identificacion");
        model.addColumn("Numero Identificacion");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Estado Civil");
        model.addColumn("Sexo");
        model.addColumn("Dirección");
        model.addColumn("Telefono");
        model.addColumn("Fecha Nacimiento");
        
        // Call DAO Funcionarios
        FuncionarioDao funcionariosDao = new FuncionarioImpl();
        
        // Add data to the model
         for(Funcionario _funcionario : funcionariosDao.getAllFuncionarios() )
        {
           // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define the desired date format
           // String formattedDate = _funcionario.getFechaNacimiento().format(formatter); // Format the date
            Object [] fetch = {_funcionario.getFuncionarioId(),
                                _funcionario.getTipoIdentificacion(),
                                _funcionario.getNumeroIdentificacion(),
                                _funcionario.getNombres(),
                                _funcionario.getApellidos(),
                                _funcionario.getEstadoCivil(),
                                _funcionario.getSexo(),
                                _funcionario.getDireccion(),
                                _funcionario.getTelefono(),
                                _funcionario.getFechaNacimiento()
            };
            
            //System.out.println(_funcionario.getFechaNacimiento());
                
            model.addRow(fetch);
        }
         
         // Set auto resize mode to adjust column widths
        jTableFuncionarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jlblAppName = new javax.swing.JLabel();
        txtSearchEmpleado = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelFuncionarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFuncionarios = new javax.swing.JTable();
        jPanelActions = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        JPanelGrupoFamiliar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableGrupoFamiliar = new javax.swing.JTable();
        JPanelInformacionAcademica = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableInformacionAcademica = new javax.swing.JTable();
        jlbFechaCompleta = new javax.swing.JLabel();
        jlblFecha = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblPais = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlblAppName.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N

        txtSearchEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchEmpleadoActionPerformed(evt);
            }
        });
        txtSearchEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchEmpleadoKeyPressed(evt);
            }
        });

        jPanelFuncionarios.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Funcionarios"));

        jTableFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFuncionarios);

        javax.swing.GroupLayout jPanelFuncionariosLayout = new javax.swing.GroupLayout(jPanelFuncionarios);
        jPanelFuncionarios.setLayout(jPanelFuncionariosLayout);
        jPanelFuncionariosLayout.setHorizontalGroup(
            jPanelFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFuncionariosLayout.setVerticalGroup(
            jPanelFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFuncionariosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/lapiz.png"))); // NOI18N
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/borrar.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelActionsLayout = new javax.swing.GroupLayout(jPanelActions);
        jPanelActions.setLayout(jPanelActionsLayout);
        jPanelActionsLayout.setHorizontalGroup(
            jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelActionsLayout.setVerticalGroup(
            jPanelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addGap(21, 21, 21))
        );

        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/mas.png"))); // NOI18N
        btnCrear.setText("Funcionario");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        JPanelGrupoFamiliar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Grupo Familiar"));

        jTableGrupoFamiliar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableGrupoFamiliar);

        javax.swing.GroupLayout JPanelGrupoFamiliarLayout = new javax.swing.GroupLayout(JPanelGrupoFamiliar);
        JPanelGrupoFamiliar.setLayout(JPanelGrupoFamiliarLayout);
        JPanelGrupoFamiliarLayout.setHorizontalGroup(
            JPanelGrupoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelGrupoFamiliarLayout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        JPanelGrupoFamiliarLayout.setVerticalGroup(
            JPanelGrupoFamiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelGrupoFamiliarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPanelInformacionAcademica.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Información Académica"));

        jTableInformacionAcademica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableInformacionAcademica);

        javax.swing.GroupLayout JPanelInformacionAcademicaLayout = new javax.swing.GroupLayout(JPanelInformacionAcademica);
        JPanelInformacionAcademica.setLayout(JPanelInformacionAcademicaLayout);
        JPanelInformacionAcademicaLayout.setHorizontalGroup(
            JPanelInformacionAcademicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelInformacionAcademicaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JPanelInformacionAcademicaLayout.setVerticalGroup(
            JPanelInformacionAcademicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelInformacionAcademicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jlbFechaCompleta.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlbFechaCompleta.setText("jLabel2");

        jlblFecha.setText("label1");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/reloj.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/calendario.png"))); // NOI18N

        jlblPais.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlblPais.setText("jLabel2");

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jlblAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlbFechaCompleta)
                        .addGap(151, 151, 151)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(jlblPais)
                        .addGap(157, 157, 157))
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(JPanelGrupoFamiliar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(JPanelInformacionAcademica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                                .addComponent(txtSearchEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(739, 739, 739)
                                .addComponent(btnCrear))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelFuncionarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlblAppName)
                        .addComponent(jlbFechaCompleta, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jlblPais))
                .addGap(14, 14, 14)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnCrear))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanelActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JPanelGrupoFamiliar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPanelInformacionAcademica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jPanelPrincipal.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jTableFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionariosMouseClicked
        
        
        // Get Value
//        int row = jTableFuncionarios.getSelectedRow();
//        DefaultTableModel model = (DefaultTableModel)jTableFuncionarios.getModel();
//        int funcionarioID = Integer.parseInt(model.getValueAt(row, 0).toString());
//        System.out.println(funcionarioID);
        try {
            jTableFuncionarios.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    //int row_table = jTableFuncionarios.getSelectedRow();
                    //int funcionarioID = Integer.parseInt(model.getValueAt(row_table, 0).toString());
                    String numeroDocumentoFuncionario = (String) jTableFuncionarios.getValueAt(jTableFuncionarios.getSelectedRow(),2);
                    
                    // Filter GrupoFamiliar
                    DefaultTableModel ModelGF = (DefaultTableModel)jTableGrupoFamiliar.getModel(); 
                    TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(ModelGF);
                    jTableGrupoFamiliar.setRowSorter(tr);
                    tr.setRowFilter(RowFilter.regexFilter(numeroDocumentoFuncionario.trim()));
                    //filterGrupoFamiliar(funcionarioID);
                    DefaultTableModel ModelIA = (DefaultTableModel)jTableInformacionAcademica.getModel(); 
                    TableRowSorter<DefaultTableModel> trIA = new TableRowSorter<>(ModelIA);
                    jTableInformacionAcademica.setRowSorter(trIA);
                    trIA.setRowFilter(RowFilter.regexFilter(numeroDocumentoFuncionario.trim()));
                }
            
            });
        } catch (Exception e) {
        }
        
         
        
    }//GEN-LAST:event_jTableFuncionariosMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
        int rowsAffected = 0;
        int row = jTableFuncionarios.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTableFuncionarios.getModel();
        int funcionarioID = Integer.parseInt(model.getValueAt(row, 0).toString());
        String tp_documento = model.getValueAt(row,1).toString();
        String documento = model.getValueAt(row,2).toString();
        String nombre = model.getValueAt(row, 3).toString();
        String apellido = model.getValueAt(row, 4).toString();
        
         // Call DAO Funcionarios
        FuncionarioDao funcionariosDao = new FuncionarioImpl();
        
        
        // check
        String message = "¿Está seguro de eliminar a " + nombre + " " + apellido +" \nIdentificado con " + tp_documento + " No. "+ documento + " ?";
        String title = "Confirmation";
        int optionType = JOptionPane.YES_NO_OPTION;

        int result = JOptionPane.showOptionDialog(null, message, title, optionType, JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if (result == JOptionPane.YES_OPTION) {
             // delete
            rowsAffected = funcionariosDao.deleteFuncionario(funcionarioID);
            
            if (rowsAffected > 0) {
                String _message = "Usuario Eliminado correctamente";
                String _title = "Information";
                JOptionPane.showMessageDialog(null, _message, _title, JOptionPane.INFORMATION_MESSAGE);
                loadTables();
                
            } else {
                String _message = "No se pudo eliminar al usuario.";
                String _title = "Error";
                JOptionPane.showMessageDialog(null, _message, _title, JOptionPane.ERROR_MESSAGE);
            }
            
            loadJTableFuncionarios();
        } else if (result == JOptionPane.NO_OPTION) {
           
            
        }
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
      // TODO add your handling code here:
//        int row = jTableFuncionarios.getSelectedRow();
//        DefaultTableModel model = (DefaultTableModel)jTableFuncionarios.getModel();
//        int funcionarioID = Integer.parseInt(model.getValueAt(row, 0).toString());
//        
//        
//         Open Window
//        FrmUpdateFuncionarios frmUpdate = new FrmUpdateFuncionarios(funcionarioID);
//        frmUpdate.setVisible(true);
//        
        
        //
        try {
            jTableFuncionarios.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    //int row_table = jTableFuncionarios.getSelectedRow();
                    int funcionarioID = (int) jTableFuncionarios.getValueAt(jTableFuncionarios.getSelectedRow(),0);
                    System.out.println(funcionarioID);
                    // Open Window
                    FrmUpdateFuncionarios frmUpdate = new FrmUpdateFuncionarios(funcionarioID);
                    frmUpdate.setVisible(true);
                   
                }
            
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarMouseClicked

    private void txtSearchEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchEmpleadoKeyPressed
        // Filter Funcionarios
        DefaultTableModel ModelF = (DefaultTableModel)jTableFuncionarios.getModel(); 
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(ModelF);
        jTableFuncionarios.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txtSearchEmpleado.getText().trim()));
        
        
 
        
    }//GEN-LAST:event_txtSearchEmpleadoKeyPressed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // Open new Form
        FrmCreateFuncionarios frmCreate = new FrmCreateFuncionarios();
        frmCreate.setVisible(true);
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtSearchEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchEmpleadoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCrudFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCrudFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCrudFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCrudFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelGrupoFamiliar;
    private javax.swing.JPanel JPanelInformacionAcademica;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelActions;
    private javax.swing.JPanel jPanelFuncionarios;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableFuncionarios;
    private javax.swing.JTable jTableGrupoFamiliar;
    private javax.swing.JTable jTableInformacionAcademica;
    private javax.swing.JLabel jlbFechaCompleta;
    private javax.swing.JLabel jlblAppName;
    private java.awt.Label jlblFecha;
    private javax.swing.JLabel jlblPais;
    private javax.swing.JTextField txtSearchEmpleado;
    // End of variables declaration//GEN-END:variables
}
