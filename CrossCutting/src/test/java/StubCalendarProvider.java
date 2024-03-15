import CrossCutting.ICalendarProvider;

public class StubCalendarProvider implements ICalendarProvider
{

    private int dayOfWeek;

    public void setDayOfWeek(int dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
    }


    @Override
    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
