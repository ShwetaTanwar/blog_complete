package initializ.blog.config;

public class AppConstansts {//Avoid hard-code
	public static final String PAGE_NUMBER="0";
	public static final  String PAGE_SIZE="10";
	public static final String SORT_BY="postId";
	public static final String SORT_DIR="asc";
	public static final Integer ROLE_NON_ADMIN=2;
	public static final Integer ROLE_ADMIN=1;
	public static final String [] PUBLIC_URLS= {   
			
			"/api/auth/**",
            "/v3/api-docs",//for json access
            "/v2/api-docs",//for swagger-api uses
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**"
   };

}
