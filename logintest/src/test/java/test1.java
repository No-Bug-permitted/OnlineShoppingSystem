import org.junit.jupiter.api.Test;

import static org.example.logintest.utils.JwtUtil.parseToken;

public class test1 {
    @Test
    public void test11(){
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsicGhvbmUiOiIxNTk3MzI0Njg4OSIsImlkIjoxN30sImV4cCI6MTczMTc0OTgwOX0.95tT2XACo9_xeJxCvzRyVanXqe6RdAVPiYb2tgyfemM";
        System.out.println(parseToken(token));
    }

}
