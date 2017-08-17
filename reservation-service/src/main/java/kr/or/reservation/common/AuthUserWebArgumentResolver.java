package kr.or.reservation.common;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserWebArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 파라미터중에서 AuthUser라는 어노테이션이 붙은 값을 요청한다.
		AuthUser loginUser = parameter.getParameterAnnotation(AuthUser.class);
		// 어노테이션이 붙지 않을 경우는 false, 붙어 있을 경우 true를 반환한다.
		if (loginUser == null)
			return false;
		else
			return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		AuthUser loginUser = parameter.getParameterAnnotation(AuthUser.class);
		// AuthUser어노테이션이 붙어있지 않을 경우 WebArgumentResolver.UNRESOLVED를 반환한다. 값을 할당하지 않겠다는
		// 의미이다.
		if (loginUser == null) {
			return WebArgumentResolver.UNRESOLVED;
		}

		// NaverLoginUser객체를 전달한다. 여기까지 코드만 본다면 메소드(@AuthUser NaverLoginUsaer 변수명) 와 같은
		// 형태의 메소드가 있어야 해당 코드가 동작한다.
		/*
		 * NaverLoginUser naverLoginUser = new NaverLoginUser();
		 * naverLoginUser.setName("resolveArgument에서 넣어준 이름");
		 */
		String name = webRequest.getParameter("name");
		return name;
	}

}
