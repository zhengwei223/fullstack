package javacommon.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUnmappingController {
		@RequestMapping("/api/**")
	    public void unmappedRequest(HttpServletRequest request) {
			 throw new ServiceException("资源不存在 " + request.getRequestURI(),ErrorCode.NOT_FOUND);
	    }

}
