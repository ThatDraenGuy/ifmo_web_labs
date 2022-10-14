package tags;

import domain.AttemptInfo;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;

public class PrinterTag extends SimpleTagSupport {
    @Getter
    @Setter
    private Collection<?> collection;
    @Getter
    @Setter
    private Duration duration;


    @Override
    public void doTag() throws JspException, IOException {
        try {
            @SuppressWarnings({"unchecked"})
            Collection<AttemptInfo> collection = (Collection<AttemptInfo>) this.collection;
            JspWriter writer = getJspContext().getOut();
            for (AttemptInfo attemptInfo : collection) {
                if (attemptInfo.execTime().compareTo(duration) < 0) {
                    writer.println("<p>");
                    writer.println(attemptInfo);
                    writer.println("</p>");
                }
            }
        } catch (ClassCastException e) {
            throw new JspException("Invalid collection type - not collection of AttemptInfo");
        }

    }
}
