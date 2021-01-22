package platform.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import platform.util.DateTimeUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "code")
@SuppressWarnings("unused")
public class Code implements Serializable {
    @Id
    @JsonIgnore
    @GeneratedValue
    private UUID id;

    @Lob
    @NotEmpty
    @Size(max = 100_000)
    private String code;

    @Min(0)
    private long time;

    @Min(0)
    private int views;

    @JsonIgnore
    private boolean timeRestricted;

    @JsonIgnore
    private boolean viewsRestricted;

    @CreationTimestamp
    private LocalDateTime date;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updateDate;

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        if (time > 0) {
            this.timeRestricted = true;
        }
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
        if (views > 0) {
            this.viewsRestricted = true;
        }
    }

    public String getDate() {
        return DateTimeUtils.format(date);
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public boolean isViewsRestricted() {
        return viewsRestricted;
    }

    @JsonIgnore
    public boolean isRestricted() {
        return isTimeRestricted() || isViewsRestricted();
    }

    public boolean view() {
        boolean viewsExpired = views == 0;
        views = Math.max(0, views - 1);

        long difference = ChronoUnit.SECONDS.between(
                updateDate, LocalDateTime.now());

        boolean timeExpired = time <= difference;
        time = Math.max(0, time - difference);

        return viewsRestricted && viewsExpired
            || timeRestricted && timeExpired;
    }
}
