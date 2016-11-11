#SUMMARY
To determine the "hot spots" of the application, I used VisualVM to find out which methods consumed more percentage of time when software run. I set the argument to be 15. And set a pattern for subsequent test in advance. It could make sure that the test pattern is same everytime. That could be used for manual test and easily analyze the change when I modified code in order to improve the performance. Therefore, from the profiling consequence of CPU, I found the convertToInt(),backup() and runContinuous() in MainPanel.java have low performances. And the toString() in Cell.java too. Because when the program complete the whole progress to acquire the stable solution for pre-load pattern, the percentage of self time of those are much higher then others except toString().

(1)In the convertToInt(), method contains a while loop for adding numbers of Zero before the input integer and transfer the type to the String. But the return value is to parse the constructed string to integer. So the while loop does not provide any useful function in this method. So I removed this part and return the input integer directly. Because the while loop will consume much time and memory space. 
(2)For backup(), it does not explicitly have great effect for performance. But it will be called in runContinuous() and runContinuous() will slow the performance of program. Consequently, the code tells the nested for loop will comsume much time because the Big-O of it is n-square. In java, there are two ways to copy array and there will be much faster than using for loops. In my modified code, I used .clone() but not System.arrayCopy(). 
(3)About runContinuous(), I already modified backup() to improve the performance. But in the while loop, the method forced thread to sleep for 20ms for each loop. And the add useless for loop to do some calculation which would not change any values of variables in the program. There all will influence the performance. So I removed all of them tring to accelerate. But it used the manual test to find out whether will it work well.
(4)The last method is toString(). The method in Cell.java concatenate the text of button repeatly. Then it just extracts the text block as long as the original text. That means the generated string is useless. It just comsume CPU time. Then I get rid of the useless part in order to increase the executing speed.

For the manual test, the runContinuous() does not contain any return values. So it is very hard to conduct unit test. Therefore, I choose the manual test to test it. To the original method, it calls a while loop to keep program running. That will response the operation when we click the button. So what I do it to add the breakpoint and call debug mode to find out the value for each step. When program running, I preload a certain pattern for test. Then observe each step when I clicked run button. The result told me the 'origR' and '_r' did nothing about the function except increase the executing time. And for each loop, it forced thread to sleep 20ms. Therefore, in the modified version, I removed for loop and relative parameters, and removed thread sleeping code. After doing this, I debug the code, and execute it step to step. I found what I changed would not make any influence about other methods or parameters. So I run program to see whether it will effect the program running and consequence for the preloaded pattern. After running it, I found the result of it is identical with what the original method did. I believe what I modified would not impact the whole program.

All the methods except the runContinuous() has been add some codes to improve the robustness.
>The stop running condition is when pattern becomes stable or transformate between several pattern.

#Screen Shot
##The original performance
###Performance
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/BEFORE_MODIFED.png)
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/BEFORE_MODIFIED2.png)
###Running Result
####original pattern
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/BEFORE_UPLOAD.png)
####first step
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/BEFORE_FIRSTSTEP.png)
####result
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/BEFORE_RESULT.png)
___
##The improved performance 
###Performance
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/AFTER_MODIFIED.png)
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/AFTER_MODIFIED2.png)
###Running Result
####original pattern
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/MODIFIED_UPLOAD.png)
####first step
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/MODIFIED_FIRSTSTEP.png)
####result
![alt text](https://github.com/ruinan/SlowLifeGUI/blob/master/screenshot/MODIFIED_RESULT.png)

