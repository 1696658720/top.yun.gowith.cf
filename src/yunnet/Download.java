package yunnet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String downloadurl;
	private String filename;
	public Download() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		downloadurl=request.getParameter("downloadFile");
		String notic="";
		//通过定义的名字获取表单信息
		if(downloadurl.indexOf("/")!=-1&&(downloadurl.indexOf("http://")==0||downloadurl.indexOf("https://")==0)){
			filename=downloadurl.substring(downloadurl.lastIndexOf("/")+1);
			//System.out.println(filename);
			RunnablePool();
			notic="正在离线下载"+filename+"文件！";
		}else{
			notic="输入的下载地址不正确！";
		}
		request.setAttribute("message",notic);
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	//获取并且返回文件大小
	public static long getFileLength(String urlLocation) throws IOException {
		URL url = null;
		if (urlLocation != null) {
			url = new URL(urlLocation);
		}
		//连接下载地址
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(5000);
		//设置连接方式
		conn.setRequestMethod("GET");
		long len = conn.getContentLength();
		return len;
	}
	
	//线程池
	public void RunnablePool() throws IOException {
		long len = getFileLength(downloadurl);
		for(int i=0;i<12;i++)
        {
            long start=i*len/12;
            long end = (i+1)*len/12-1;
            if(i==11)
            {
                end =len;
            }
			MoreDownload md=new MoreDownload(filename,downloadurl,start,end);
			Thread dmd=new Thread(md);
			dmd.start();
        }
	}
}
