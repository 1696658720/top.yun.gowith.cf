package yunnet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MoreDownload implements Runnable {
	private String filePath="Filedir";
	private String downloadurl;
	private String filename;
	private long start;
	private long end;

	public MoreDownload(String filename, String downloadurl,long start,long end) {
		this.filename = filename;
		this.downloadurl = downloadurl;
		this.start=start;
		this.end=end;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
        {
            HttpURLConnection conn = getHttp();
            //设置请求属性
            conn.setRequestProperty("Range", "bytes=" + start + "-" + end);
            //文件夹创建
            File file = new File(filePath+"/"+filename);
            RandomAccessFile out = null;
            if (file != null)
            {
                out = new RandomAccessFile(file, "rwd");
            }
            out.seek(start);
            InputStream in = conn.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1)
            {
                out.write(b, 0, len);
            }
            in.close();
            out.close();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
	}
	//连接下载服务器
	public HttpURLConnection getHttp() throws IOException
    {
        URL url = null;
        if (downloadurl != null)
        {
            url = new URL(downloadurl);
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        return conn;
    }

}
