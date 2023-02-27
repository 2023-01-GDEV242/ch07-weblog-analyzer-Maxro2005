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
}
