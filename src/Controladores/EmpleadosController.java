package Controladores;

import Modelos.*;
import Vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author umg
 */
public class EmpleadosController implements ActionListener{
 frmEmpleados VistaEmpleados;
 frmPrincipal VistaPrincipal;
 EmpleadosModel ModeloEmpleado;
 
    

    public EmpleadosController(frmEmpleados VistaEmpleados, frmPrincipal VistaPrincipal, EmpleadosModel ModeloEmpleado) {
        this.VistaEmpleados = VistaEmpleados;
        this.VistaPrincipal = VistaPrincipal;
        this.ModeloEmpleado = ModeloEmpleado;
        
        /*LEVANTAR LAS VISTAS*/
      this.VistaPrincipal.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
      this.VistaPrincipal.setVisible(true);
      
      /*PONER A LA ESCUCHA LOS BOTONES*/
      this.VistaEmpleados.btn_Agregar.addActionListener(this);
      this.VistaEmpleados.btn_Editar.addActionListener(this);
      this.VistaEmpleados.btnEliminar.addActionListener(this);
        
      /*REALIZAR LA CONEXION*/
            
            //Limpiar la tabla Vista Empleados
                DefaultTableModel TablaModelo = new DefaultTableModel();
                TablaModelo.setRowCount(0);
                TablaModelo.setColumnCount(0);
                this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
      
            //prepara el modelo de la tabla
                    TablaModelo.addColumn("ID");
                    TablaModelo.addColumn("APELLIDOS");
                    TablaModelo.addColumn("NOMBRE");
                    TablaModelo.addColumn("TELEFONO");
                    
      /* LEVANTAR EL MODELO Y LOGRAR RECORRER EL RESULTSET*/
        //Captar el resultado que viene del Modelo desde el método LISTARDATOS
                ResultSet rstEmpleados =  this.ModeloEmpleado.ListarDatos();
               
                    try
                    {
                       
                    while(rstEmpleados.next())
                    {
                     TablaModelo.addRow(new Object[]{rstEmpleados.getInt("idEmpleado"),rstEmpleados.getString("Apellidos"),rstEmpleados.getString("Nombre"),rstEmpleados.getString("Telefono")});  
                    }  
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, "Algo hizo falta..."+e);
                    }
                   
            //PASAR EL MODELO CREADO A LA TABLA DE LA VISTA EMPLEADOS        
                    this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
        
        /*LEVANTAR LA VISTA EMPLEADOR*/
      this.VistaEmpleados.setLocationRelativeTo(null);
      this.VistaEmpleados.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.VistaEmpleados.btn_Editar)
        {
            this.ModeloEmpleado.Actualizar(1, "Raton", "Rata", "32145");
        }
    }

    
    
 
 
}
