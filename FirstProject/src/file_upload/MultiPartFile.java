package file_upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.*;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/multiPartServlet")
@MultipartConfig
public class MultiPartFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int UPLOAD_MAX_SIZE = 1024 * 1024 * 1; // 1 MB
	private static final Path LOC = Paths.get(System.getProperty("user.home", "TempStorage"));
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiPartFile() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init()
    {
    	try 
    	{
			Files.createDirectory(MultiPartFile.LOC);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DO NOT USE getRealPath() <- IT IS NOT PORTABLE, and BAD practice
		
		Part filePart = request.getPart("file");  //  Retrieve <input type = "file" name = "file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();  //  MSIE fix as MSIE browser incorrectly sends the full clinet-side file path along the name instead of only the file name...this is also a security leak according to the post.
		InputStream fileContent = filePart.getInputStream();
		
		Scanner scanner = new Scanner(fileContent, StandardCharsets.UTF_8);  //  Create scanner with charset UTF_8
		String text = null;
		text = scanner.useDelimiter("\\A").next(); //  Use very beginning of the file content as delimiter and do .next, so reads the entire file
		
		
		response.getWriter().append("Content of " + fileName + ": " + text);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}