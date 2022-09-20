package info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Random;


public record ReactionsInfo(@JsonProperty("hit") String[] hit, @JsonProperty("miss") String[] miss) {
    public String randomHit() {
        Random random = new Random();
        return hit[random.nextInt(hit.length)];
    }
    public String randomMiss() {
        Random random = new Random();
        return miss[random.nextInt(miss.length)];
    }

    public static ReactionsInfo empty() {
        return new ReactionsInfo(new String[]{""}, new String[]{""});
    }
}
