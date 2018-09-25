package com.realdolmen.brentvw.rest.providers;

import com.realdolmen.brentvw.rest.domain.Book;

import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Stateless
@Produces(MediaType.TEXT_HTML)
public class BookToHtmlProvider implements MessageBodyWriter<Book> {
    @Context
    private UriInfo uriInfo;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Book.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Book book, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Book book, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        PrintWriter writer = new PrintWriter(entityStream);
        String style = "/* Sets up our marquee, and inner content */\n" +
                ".marquee {\n" +
                "  overflow: hidden;\n" +
                "  position: relative;\n" +
                "  padding-left: 100%;\n" +
                "  /* Some browsers may require -webkit-animation */\n" +
                "  animation: reduce 20s linear infinite;\n" +
                "}\n" +
                "\n" +
                ".marquee__inner {\n" +
                "  white-space: nowrap;\n" +
                "  display: inline-block;\n" +
                "  /* Some browsers may require -webkit-animation */\n" +
                "  animation: scroll 10s linear infinite;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "/* Creates two white-to-transparent gradients at the ends of the marquee */\n" +
                ".marquee::before,\n" +
                ".marquee::after {\n" +
                "  z-index: 1;\n" +
                "  top: 0;\n" +
                "  left: 0;\n" +
                "  position: absolute;\n" +
                "  width: 50px;\n" +
                "  height: 100%;\n" +
                "  content: \"\";\n" +
                "  display: block;\n" +
                "}\n" +
                "\n" +
                ".marquee::after {\n" +
                "  left: auto;\n" +
                "  right: 0;\n" +
                "  transform: rotate(180deg);\n" +
                "}\n" +
                "\n" +
                "@keyframes reduce {\n" +
                "  to {\n" +
                "    padding-left: 0;\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "@keyframes scroll {\n" +
                "  to {\n" +
                "    transform: translateX( -100%) translateY(-100%);\n" +
                "  }\n" +
                "}";
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<style>"+style+"</style>");
        writer.print("<title>");
        writer.print(book.getTitle());
        writer.print("</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<div class=\"marquee\" />");
        writer.print("<h1>");
        writer.print(book.getTitle());
        writer.println("<h1>");
        writer.print("<h2>");
        writer.print(book.getAuthor());
        writer.println("</h2>");
        writer.print("<p>ISBN: ");
        writer.print(book.getIsbn());
        writer.println("</p>");

        if(book.getCoverImage() != null) {
            String url = uriInfo.getBaseUriBuilder().path("images").path("{id}").build(book.getId()).toURL().toExternalForm();
            writer.println("<img src=\""+url +"\" />");
        }

        writer.print("</div>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
    }
}
