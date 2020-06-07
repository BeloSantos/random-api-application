package vm.random.number;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class RandomNumber {


 @Positive
 private Long id;

  private int number;

  private LocalTime duration;

  private  boolean status;

  public RandomNumber(){

  }
  public RandomNumber(Long id, int number, LocalTime duration, boolean status) {
    this.id = id;
    this.number = number;
    this.duration = duration;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public LocalTime getDuration() {
    return duration;
  }

  public void setDuration(LocalTime duration) {
    this.duration = duration;
  }

  public boolean getStatus() {
    return status;
  }

  public boolean setStatus(boolean status) {
    this.status = status;
    return status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RandomNumber that = (RandomNumber) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
