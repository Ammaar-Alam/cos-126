Programming Assignment 6: Image Classifier


/******************************************************************************
 ***   Are you aware of the midsemester survey?  Please see Ed for          ***
 ***   instructions.                                                        ***
 ******************************************************************************/
Yes or no?
Yes; i did it

/******************************************************************************
 *  Approximately how many hours did it take you to complete this assignment?
 ******************************************************************************/

Number of hours: 3-4

/******************************************************************************
 *  Part 1. Some people (especially in Europe and Latin America) write a 7 with
 *  a line through the middle, while others (especially in Japan and Korea)
 *  make the top line crooked.
 *
 *  (a) Suppose that the training data consists solely of samples that do not
 *      use any of these conventions. How well do you think the algorithm will
 *      perform when you test it on different populations? What are the
 *      possible consequences?
 *
 *  (b) Now suppose that you are using a supervised learning algorithm to
 *      diagnose cancer. Suppose the training data consists of examples solely
 *      on individuals from population X but you use it on individuals from
 *      population Y. What are the possible consequences?
 ******************************************************************************/

ANSWER:

(a)
The algorithm would probabbly perform poorly when tested on populations that do
use these conventions. The model wouldn't have seen examples of 7s with those added
complications, so it wouldn't havelearned to recognize them. This means it might
misclassify these variations as other numbers or symbols. The possible consequences
would mostl likely be misinterpretation of data.


(b)
There is a risk that the algorithm might not perform as expected because there
could be genetic, environmental, lifestyle, or other differences between the two
populations that affect the identifyable characteristics of cancer. If these
differences are not accounted for in the training data, the algorithm might produce
false positives/false negatives, or other inaccurate results. The possible consequences
are severe, like misdiagnosis or inappropiate medical treatment/loss of life.

/******************************************************************************
 *  Part 2
 *
 *  (a) Which digit is misclassified the most frequently?
 *
 *  (b) For this digit,  what are the top two digits that your MultiPerceptron
 *      incorrectly predicts?
 *
 *  (c) Examine some of these misclassified images. Provide an explanation
 *      of what might have caused these misclassifications.
 ******************************************************************************/

ANSWER:

(a) Most frequently misclassified digit: 9

(b) Top two digits misclassified are: 4 and 7

(c) Explanation:
A lot of these images are very pixelated and that blurriness might be causing the
algorithm to confuse each number. If you notice the similariites between the 9 and
7 or 4, they all have a lot of "business" near the top of the number, as in that
the top fragment has a lot of strokes; even the 7 which is a less busy number
has strokes similar to that of a 9. The general pixelated nature of these pictures
compounds the already busy strokes, confusing the algorithm.


/******************************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 ******************************************************************************/

Yes or no?
there was a point my weights array was leading to double the expected resulting
vectors (left it commented), so i added a constant LEARNING_RATE variable, but
then i got check style warns. then eventually i realized i missed the part of the
assignment guidelines that shows how to properly define the array, which i
somehow ignores the first time lol

also the partner i was planning to work with cancelled last minute lol


/******************************************************************************
 *  Write any other comments here.
 ******************************************************************************/
n/a

