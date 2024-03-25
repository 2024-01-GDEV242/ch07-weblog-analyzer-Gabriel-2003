/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Jose G Torres
 * @version    2024.03.11
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Where to calculate dayly access counts.
    private int[] dayCounts;
    // Where to calculate mounthly access counts.
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    private String logWord;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String logWord)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(logWord);
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
    
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }   
    }
    
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            this.monthCounts[month]++;
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
    
    public int numberofAccess()
    {
        //This code is here so it could add up each access.
        int total = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            total += hourCounts[i];
        }
        return total;
    }
    
    public void busiestHour()
    {
        //This code will print out and tell you when is the busiest hour.
        int busiest = 0;
        int hour = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] > busiest)
            {
                busiest = hourCounts[i];
                hour = i;
            }
        }
        System.out.println("Busiest hour is " + hour + ".");
    }
    
    public void busiestDay()
    {
        //This code will print out and tell you when is the busiest day.
        int busiest = 0;
        int day = 0;
        for(int i = 0; i < dayCounts.length; i++)
        {
            if(dayCounts[i] > busiest)
            {
                busiest = dayCounts[i];
                day = i;
            }
        }
        System.out.println("Busiest hour is " + day + ".");
    }
    
    public void busiestMonth()
    {
        //This code will print out and tell you when is the busiest month.
        int busiest = 0;
        int month = 0;
        for(int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] > busiest)
            {
                busiest = monthCounts[i];
                month = i;
            }
        }
        System.out.println("Busiest hour is " + month + ".");
    }
    
    public void quietestHour()
    {
        //This code will print out and tell you when is the quietest hour.
        int quietest = hourCounts[0];
        int hour = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] < quietest)
            {
                quietest = hourCounts[i];
                hour = i;
            }
        }
        System.out.println("Quetest hour is " + hour + ".");
    }
    
    public void quietestDay()
    {
        //This code will print out and tell you when is the quietest day.
        int quietest = dayCounts[0];
        int day = 0;
        for(int i = 0; i < dayCounts.length; i++)
        {
            if(dayCounts[i] < quietest)
            {
                quietest = dayCounts[i];
                day = i;
            }
        }
        System.out.println("Quetest hour is " + day + ".");
    }
    
    public void quietestMonth()
    {
        //This code will print out and tell you when is the quietest month.
        int quietest = monthCounts[0];
        int month = 0;
        for(int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] < quietest)
            {
                quietest = monthCounts[i];
                month = i;
            }
        }
        System.out.println("Quetest hour is " + month + ".");
    }
    
    public void busiestTwoHour()
    {
        //This code will tell you when are the two busiest hours.
        int busiest = 0;
        int hour1 = 0;
        int hour2 = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] >= busiest)
            {
                busiest = hourCounts[i];
                hour2 = hour1;
                hour1 = i;
            }
        }
        System.out.println("The two busiest hours are " + hour1 + " and " + hour2 + ".");
    }
    
    public void totalAccessPerMonth()
    {
        // This code would tell you the total access pr month.
        System.out.println("Months: Counts");
        int total = 0;
        for(int i = 0; i < monthCounts.length; i++)
        {
            System.out.println(i + ": " + monthCounts[i]);
            total += monthCounts[i];
        }
        System.out.println("Total: "+ total);
    }
    
    public void averageAccessPerMonth()
    {
        System.out.println("Months: Average");
        int total = 0;
        analyzeMonthlyData();
        for(int i = 0; i < this.monthCounts.length; i++)
        {
            System.out.println(i + ": " + this.monthCounts[i]);
            total += this.monthCounts[i];
        }
        int avg = total / this.monthCounts.length; 
        System.out.println("Average: "+ avg);
    }
}
