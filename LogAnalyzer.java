/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Max Romano
 * @version 2023.02.27
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Where to calculate the daily access counts.
    private int[] dayCounts;
    // Where to calculate the monthly access counts.
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    /**
     * Create a LogAnalyzer that can take a log file to be analyzed.
     * @param fileName the name of the file
     */
    public LogAnalyzer(String fileName)
    {
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
        reader.reset();
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
        reader.reset();
    }
    
    /**
     * Print the daily counts.
     * These should have been set with a prior
     * call to analyzeDailyData.
     */
    public void printDailyCounts()
    {
        System.out.println("Day: Count");
        for(int day = 1; day < dayCounts.length; day++) {
            System.out.println(day + ": " + dayCounts[day]);
        }
    }
    
    /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
        reader.reset();
    }
    
    /**
     * Print the monthly counts.
     * These should have been set with a prior
     * call to analyzeMonthlyData.
     */
    public void printMonthlyCounts()
    {
        System.out.println("Month: Count");
        for(int month = 1; month < monthCounts.length; month++) {
            System.out.println(month + ": " + monthCounts[month]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Counts the total number of accesses recorded in the log file.
     * @return the total number of accesses
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for (int numAccesses : hourCounts)
            total += numAccesses;
        return total;
    }
    
    /**
     * Returns the busiest hour of the year.
     * @return the busiest hour
     */
    public int busiestHour()
    {
        int busiestHour = 0;
        int mostAccesses = 0;
        for (int hour = 0; hour < hourCounts.length; hour ++)
        {
            if (hourCounts[hour] > mostAccesses)
            {
                busiestHour = hour;
                mostAccesses = hourCounts[hour];
            }
        }
        return busiestHour;
    }
    
    /**
     * Returns the quietest hour of the year.
     * @return the quietest hour
     */
    public int quietestHour()
    {
        int quietestHour = 0;
        int leastAccesses = hourCounts[0];
        for (int hour = 1; hour < hourCounts.length; hour ++)
        {
            if (hourCounts[hour] < leastAccesses)
            {
                quietestHour = hour;
                leastAccesses = hourCounts[hour];
            }
        }
        return quietestHour;
    }
    
    /**
     * Finds which two-hour period is the busiest.
     * @return the first hour of the period
     */
    public int busiestTwoHour()
    {
        int busiestTwoHour = 0;
        int mostAccesses = 0;
        for (int hour = 0; hour < hourCounts.length; hour ++)
        {
            if (hour == 23)
            {
                if ((hourCounts[23] + hourCounts[0]) > mostAccesses)
                {
                    busiestTwoHour = 23;
                    mostAccesses = hourCounts[23] + hourCounts[0];
                }
            }
            else
            {
                if ((hourCounts[hour] + hourCounts[hour + 1])
                        > mostAccesses)
                {
                    busiestTwoHour = hour;
                    mostAccesses = hourCounts[hour]
                                    + hourCounts[hour + 1];
                }
            }
        }
        return busiestTwoHour;
    }
    
    /**
     * Returns the quietest day of the year.
     * @return the quietest day
     */
    public int quietestDay()
    {
        int quietestDay = 1;
        int leastAccesses = dayCounts[1];
        for (int day = 2; day < dayCounts.length; day ++)
        {
            if (dayCounts[day] < leastAccesses)
            {
                quietestDay = day;
                leastAccesses = dayCounts[day];
            }
        }
        return quietestDay;
    }
    
    /**
     * Returns the busiest day of the year.
     * @return the busiest day
     */
    public int busiestDay()
    {
        int busiestDay = 0;
        int mostAccesses = 0;
        for (int day = 1; day < dayCounts.length; day ++)
        {
            if (dayCounts[day] > mostAccesses)
            {
                busiestDay = day;
                mostAccesses = dayCounts[day];
            }
        }
        return busiestDay;
    }
    
    /**
     * Prints the total number of accesses for each month.
     */
    public void totalAccessesPerMonth()
    {
        printMonthlyCounts();
    }
}
