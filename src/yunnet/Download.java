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
		//ͨ����������ֻ�ȡ����Ϣ
		if(downloadurl.indexOf("/")!=-1&&(downloadurl.indexOf("http://")==0||downloadurl.indexOf("https://")==0)){
			filename=downloadurl.substring(downloadurl.lastIndexOf("/")+1);
			//System.out.println(filename);
			RunnablePool();
			notic="������������"+filename+"�ļ���";
		}else{
			notic="��������ص�ַ����ȷ��";
		}
		request.setAttribute("message",notic);
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	//��ȡ���ҷ����ļ���С
	public static long getFileLength(String urlLocation) throws IOException {
		URL url = null;
		if (urlLocation != null) {
			url = new URL(urlLocation);
		}
		//�������ص�ַ
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(5000);
		//�������ӷ�ʽ
		conn.setRequestMethod("GET");
		long len = conn.getContentLength();
		return len;
	}
	
	//�̳߳�
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
