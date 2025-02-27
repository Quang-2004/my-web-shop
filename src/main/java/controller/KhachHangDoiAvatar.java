package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class KhachHangDoiAvatar
 */
@WebServlet("/thay-doi-avatar")
public class KhachHangDoiAvatar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhachHangDoiAvatar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("123");
		Object obj = request.getSession().getAttribute("khachHang");
		KhachHang khachHang = null;
		if (obj != null)
			khachHang = (KhachHang) obj;
		if (khachHang != null) {
			try {
				String folder = getServletContext().getRealPath("avatar");
				System.out.println(folder);
				File file;
				int maxFileSize = 5000 * 1024;
				int maxMemSize = 5000 * 1024;

				String contentType = request.getContentType();

				if (contentType.indexOf(contentType) >= 0) {
					DiskFileItemFactory factory = new DiskFileItemFactory();

					// Quy dinh dung luong toi da cho 1 file
					factory.setSizeThreshold(maxMemSize);

					// Tao file upload
					ServletFileUpload upload = new ServletFileUpload(factory);

					upload.setSizeMax(maxFileSize);

					List<FileItem> files = upload.parseRequest(request);

					for (FileItem fileItem : files) {
						if(!fileItem.isFormField()) {
							String fileName = System.currentTimeMillis() + fileItem.getName();
							String path = folder + "\\" + fileName;
							file = new File(path);
	
							fileItem.write(file);
	
							khachHang.setDuongDanAnh(fileName);
							KhachHangDAO khachHangDAO = KhachHangDAO.getInstance();
							khachHangDAO.updateImage(khachHang);
							khachHang = khachHangDAO.selectByID(khachHang);
						}else {
							String name = fileItem.getFieldName();
							String value = fileItem.getString();
							System.out.println(name+" : "+value);
						}
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/khach-hang/ThayDoiAvatar.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
