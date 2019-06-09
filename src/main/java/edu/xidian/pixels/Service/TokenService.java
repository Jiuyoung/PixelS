package edu.xidian.pixels.Service;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import edu.xidian.pixels.Entity.User;

/**
 * TokenService
 */
@Service
public class TokenService {
    @Autowired
    StringRedisTemplate template;

    public String getToken(User user) {
        String token = "";
        String uuid = getUUID32();
        token = JWT.create().withAudience(String.valueOf(user.getAccount()))
                    .sign(Algorithm.HMAC256(uuid));
        if(hasToken(user.getAccount())){
            template.delete("token_" + user.getAccount());
        }
        template.opsForValue().set("token_" + user.getAccount(), uuid, Duration.ofHours(1));
        return token;
    }

    public void updateToken(String userAccount, String token) {
        template.expire("token_" + userAccount, 1, TimeUnit.HOURS);
    }

    public boolean hasToken(String userAccount) {
        return template.hasKey("token_" + userAccount);
    }

    public String findUUID(String userAccount) {
        return template.opsForValue().get("token_" + userAccount);
    }

    private String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}