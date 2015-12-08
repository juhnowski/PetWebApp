/*
 * Тестовое задание для Вадима
 */
package servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import petweb.PetsHelper;
import static utils.ErrorCodes.CODE_201;
import static utils.Settings.PATH_TO_TEMPLATES;

/**
 * Сервис добавления нового животного
 * @author Илья Юхновский
 */
@WebServlet(name = "AddPetServlet", urlPatterns = {"/AddPetServlet"})
public class AddPetServlet extends HttpServlet {

    public static Configuration cfg;
    public static PetsHelper ph;
    
    @Override
    public void init() throws ServletException {
        
        Configuration localInstance;
        if (cfg == null) {
		synchronized (Configuration.class) {
                    localInstance = cfg;
                    if (localInstance == null) {
			cfg = localInstance = new Configuration(Configuration.VERSION_2_3_22);
                    }
		}
	}

        try {
            cfg.setDirectoryForTemplateLoading(new File(PATH_TO_TEMPLATES));
        } catch (Exception e){
            throw new ServletException(e);
        }

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        ph = new PetsHelper();
    }

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
        
        //TODO: check token
        
        String login = request.getParameter("login");
        String nickname = request.getParameter("nickname");
        String speciesName = request.getParameter("speciesName");
        String birthdate = request.getParameter("birthdate");
        boolean male = true; //request.getParameter("male");
        
        
        ph.addPet(nickname, speciesName, new Date(), male, login);
        
        Map<String, Object> root = new HashMap<>();
        root.put("login", login);
        root.put("nickname", nickname);
        root.put("speciesName", speciesName);
        root.put("birthdate", birthdate);
        root.put("male", male);
        root.put("result", CODE_201);
        Template temp = cfg.getTemplate("addpetresult.ftl");
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try{
                temp.process(root, out);
            } catch (TemplateException te){
                out.println("],\"Error\":\"500\",\"Description\":\"Internal Server Error\"}");
            }
        }
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
