import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.Random;
import java.net.InetAddress;



public class Main
{

    public static void printUsage(Runtime runtime)
    {
        long total, free, used; //defines the variables
        int gb = 1024*1024*1024; //defines what a GB is

        total = runtime.totalMemory(); //Defines total memory
        free = runtime.freeMemory(); //Defines free memory
        used = total - free; //Defines what used memory is
        System.out.println("\nTotal Memory: " + total / gb + " GB");
        System.out.println("Memory Used: " + used / gb + " GB");
        System.out.println("Memory Free: " + free / gb + " GB");
    }

    public static void log(Object message)
    {
        System.out.println(message);
    }

    public static int calcCPU(long cpuStartTime, long elapsedStartTime, int cpuCount)
    {
        long end = System.nanoTime();
        long totalAvailCPUTime = cpuCount * (end-elapsedStartTime);
        long totalUsedCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime()-cpuStartTime;
        float per = ((float)totalUsedCPUTime*100)/(float)totalAvailCPUTime;
        log( per);
        return (int)per;
    }

    static boolean isPrime(int n)
    {
        // 2 is the smallest prime
        if (n <= 2)
        {
            return n == 2;
        }
        // even numbers other than 2 are not prime
        if (n % 2 == 0)
        {
            return false;
        }
        // check odd divisors from 3
        // to the square root of n
        for (int i = 3, end = (int)Math.sqrt(n); i <= end; i += 2)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
    }
    public static void main(String [] args) throws Exception
    {
        int gb = 1024*1024*1024;
        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
                java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        long physicalMemorySize = os.getTotalPhysicalMemorySize();
        System.out.println("IP Details\n");
        System.out.println("Your IP is " +InetAddress.getLocalHost()); //Grabs the computers IP and Hostname (Bill-PC/192.168.1.123)
        System.out.println("\n--");
        System.out.println("RAM Details\n");
        System.out.println("Total  : " + physicalMemorySize / gb + " GB "); //Grabs the total amount of RAM in the system and prints it
        long physicalfreeMemorySize = os.getFreePhysicalMemorySize(); //Grabs the current
        System.out.println("Usable : " + physicalfreeMemorySize / gb + "  GB");
        if (physicalfreeMemorySize >= physicalMemorySize || physicalfreeMemorySize > physicalMemorySize%10) {
            System.out.println("\nWARNING!!! you are running low on RAM, close some programs or close some Chrome tabs!");
        }
        else
            System.out.println("\nYou're doing good on RAM.");
        /* DISC SPACE DETAILS */
        File diskPartition = new File("C:");
        File diskPartition1 = new File("D:");
        long totalCapacity = diskPartition.getTotalSpace() / gb;
        long totalCapacity1 = diskPartition1.getTotalSpace() / gb;
        float freePartitionSpace = diskPartition.getFreeSpace() / gb;
        float freePartitionSpace1 = diskPartition1.getFreeSpace() / gb;
        System.out.println("\n--");
        System.out.println("Storage Details\n");
        System.out.println("C drive usage: " + freePartitionSpace + "GB out of " + totalCapacity + " GB");
        System.out.println("D drive usage: " + freePartitionSpace1 + "GB out of " + totalCapacity1 +" GB");
        if(freePartitionSpace <= totalCapacity%10 || freePartitionSpace1 <= totalCapacity1%10)
        {
            System.out.println("\nIt looks like you're almost out of storage\n");
        }
        else
            System.out.println("\nYou're pretty good on storage\n");

            long start = System.nanoTime();
            // log(start);
            //number of available processors;
            int cpuCount = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
            Random random = new Random(start);
            int seed = Math.abs(random.nextInt());
            System.out.println("--");
            log("CPU Details \n");
            int primes = 10000;
            //
            long startCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
            start = System.nanoTime();
            while(primes != 0)
            {
                if(isPrime(seed))
                {
                    primes--;
                }
                seed++;

            }
            float cpuPercent = calcCPU(startCPUTime, start, cpuCount);
            log("CPU Usage : " + cpuPercent + "% ");
        }

}
/Test123    
