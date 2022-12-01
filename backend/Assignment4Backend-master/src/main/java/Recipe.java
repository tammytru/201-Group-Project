

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Recipe
 */
@WebServlet("/Recipe")
public class Recipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recipe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postID = Integer.parseInt(request.getParameter("postID"));
		
		try {
			Post post = JDBCConnector.getPost(postID);
			if(post == null) {
				response.setContentType("application/json");
				response.setStatus(400);
				PrintWriter out = response.getWriter();
				
				Gson gson = new Gson();
				JsonObject obj = new Gson().fromJson("{\'error\': \"There was an exception\"}", JsonObject.class);
				out.print(gson.toJson(obj));
				out.flush();
			}
			response.setContentType("application/json");
			response.setStatus(200);
			PrintWriter out = response.getWriter();
			
			Gson gson = new Gson();
			String jsonString = gson.toJson(post);
			out.print(jsonString);
			out.flush();
		}catch (Exception e) {
			response.setContentType("application/json");
			response.setStatus(400);
			PrintWriter out = response.getWriter();
			
			Gson gson = new Gson();
			JsonObject obj = new Gson().fromJson("{\'error\': \"There was an exception\"}", JsonObject.class);
			out.print(gson.toJson(obj));
			out.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// create recipe
		String date = request.getParameter("date");
		String image = request.getParameter("image");
		String name = request.getParameter("name");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String text = request.getParameter("text");
		int userID = Integer.parseInt(request.getParameter("userID"));

		try {
			int postID = JDBCConnector.createPost(date, image, name, rating, text, userID);
			response.setContentType("application/json");
			response.setStatus(200);
			PrintWriter out = response.getWriter();
			
			Gson gson = new Gson();
			JsonObject obj = new Gson().fromJson("{\'postID\': " + postID + "}", JsonObject.class);
			out.print(gson.toJson(obj));
			out.flush();
		}catch (Exception e) {
			response.setContentType("application/json");
			response.setStatus(400);
			PrintWriter out = response.getWriter();
			
			Gson gson = new Gson();
			JsonObject obj = new Gson().fromJson("{\'postID\': " + -1 + "}", JsonObject.class);
			out.print(gson.toJson(obj));
			out.flush();
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postID = Integer.parseInt(request.getParameter("postID"));
		
		try {
			String message = JDBCConnector.deletePost(postID);
			
			response.setContentType("application/json");
			response.setStatus(200);
			PrintWriter out = response.getWriter();
			
			Gson gson = new Gson();
			JsonObject obj = new Gson().fromJson("{\'status\': \"" + message + "\"}", JsonObject.class);
			out.print(gson.toJson(obj));
			out.flush();
		}catch (Exception e) {
			response.setContentType("application/json");
			response.setStatus(400);
			PrintWriter out = response.getWriter();
			
			Gson gson = new Gson();
			JsonObject obj = new Gson().fromJson("{\'error\': \"There was an exception\"}", JsonObject.class);
			out.print(gson.toJson(obj));
			out.flush();
		}
	}

}
