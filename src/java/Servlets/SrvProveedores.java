/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import AccesoDatos.AccesoProveedores;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lab
 */
public class SrvProveedores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        

    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         try {
            ResultSet res = null;
            AccesoProveedores provee = new AccesoProveedores(); //instancia un objeto de la clase exportada
            String provrut = "";
            String provrazon = "";
            String provciudad = "";
            String provregion = "";
            String provdireccion = "";
            String boton="";
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Proveedores</title>");
            out.println("</head>");
            out.println("<body>");
            
            //no se puede comparar string usando ==
            if(!"".equals(request.getParameter("PROVRUT")))
                provrut=request.getParameter("PROVRUT");
            if(!"".equals(request.getParameter("PROVRAZON")))
                provrazon=request.getParameter("PROVRAZON");
            if(!"".equals(request.getParameter("PROVCIUDAD")))
                provciudad=request.getParameter("PROVCIUDAD");
            if(!"".equals(request.getParameter("PROVREGION")))
                provregion=request.getParameter("PROVREGION");
            if(!"".equals(request.getParameter("PROVDIRECCION")))
                provdireccion=request.getParameter("PROVDIRECCION");
            
            boton=request.getParameter("Boton");
            
            if (boton.equals("Agregar")){
                provee.Insertar(provrut,provrazon,provciudad,provregion,provdireccion);
                out.println("se ha agregado 1 fila");
            }
            if(boton.equals("Actualizar")){
                provee.Actualizar(provrut,provrazon,provciudad,provregion,provdireccion);
                out.println("se ha actualizado 1 fila");
            }
            if(boton.equals("Eliminar")){
                provee.Eliminar(provrut);
                out.println("se ha eliminado 1 fila");
            }
            if(boton.equals("Listado"))
            {
              //SOLO SI TODOS LOS CAMPOS ESTAN VACIOS SE ENTREGA LA TABLA COMPLETA
              //mismo problema, no se puede comparar string usando ==
              if(!"".equals(provrut)) //si rut no es vacio
              {
                  res = provee.BuscarPorRut(provrut);
              }else
              {
                  if( (!"".equals(provregion)) && (!"".equals(provciudad)) )
                  { //busca por ciudad y region 
                      res = provee.BuscarPorRegionYCiudad(provregion, provciudad);
                  }else
                  {
                      if(!"".equals(provregion))
                      {//busca por region
                          
                          res = provee.BuscarPorRegion(provregion);     
                      }else
                      {
                          if(!"".equals(provrazon))
                          {//busca por razon
                              res = provee.BuscarPorRazon(provrazon);
                          }else
                          {//todos los campos en blanco, o solo la ciudad.. 
                              
                              if(!"".equals(provciudad))
                              {//busca por ciudad
                                  res=provee.BuscarPorCiudad(provciudad);
                              }else
                              {
                                  //muestra toda la tabla
                                  res = provee.Listado();
                                  
                              }
                              
                               
                          }
                      }
                  }
              }
              out.println("<table border=1>");
              out.println("<tr><td>LISTADO DE PROVEEDORES </td></tr> ");
              out.println("<tr><td>Rut</td><td>Razon</td><td>Ciudad</td><td>Region</td><td>Direccion</td></tr>");
               
              int c = 0;
              while (res.next()) 
              {
                provrut = res.getString("PROVRUT");
                provrazon = res.getString("PROVRAZON");
                provciudad = res.getString("PROVCIUDAD");
                provregion = res.getString("PROVREGION");
                provdireccion = res.getString("PROVDIRECCION");
                out.println("<tr><td>" + provrut + "</td><td>" + provrazon + "</td><td>" + provciudad + "</td><td>" + provregion + "</td><td>" + provdireccion + "</td></tr>");
                c++;
               }
              if(c==0){
                  out.println("<tr><td> No hay coincidencias</td></tr>");
              }
              out.println("</table>");
            } //fin if "listado"      
            out.println("</body>");
            out.println("</html>");
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(SrvProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
