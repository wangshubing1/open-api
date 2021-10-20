package cn.com.belle.bdc.openapi.conf;

import cn.com.belle.bdc.openapi.common.HttpHelper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * 从Filter获取post参数时，当请求格式和application/json时，
 * 当从请求中获取流以后，流被filter中的这个 inputStreamToString(InputStream in) 这个方法处理后就被“消耗”了，
 * 这会导致，chain.doFilter(request, res)这个链在传递 request对象的时候，
 * 里面的请求流为空，导致责任链模式下，其他下游的链无法获取请求的body,
 * 从而导致程序无法正常运行，这也使得我们的这个filter虽然可以获取请求信息，
 * 但是它会导致整个应用程序不可用，那么它也就失去了意义
 * 所以需要将取出来的字符串，再次转换成流，然后把它放入到新request 对象中，
 * 在chain.doFiler方法中 传递新的request对象；要实现这种思路，需要自定义一个类
 * BodyReaderRequestWrapper
 *
 */
public class BodyReaderRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body = null;

    public BodyReaderRequestWrapper(HttpServletRequest request) {
        super(request);
        System.out.println("-------------------------------------------------");
        Enumeration<String> e = request.getHeaderNames();
        while(e.hasMoreElements()){
            String name = (String) e.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+" = "+value);

        }
        body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));

    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public String getHeader(String name) {
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return super.getHeaderNames();
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        return super.getHeaders(name);
    }
}
