Programming Assignment 4:  Recursive Graphics


/******************************************************************************
 *  Approximate number of hours to complete this assignment
 ******************************************************************************/

Number of hours: 1-3 (For first two) then like 12+ for the second one (i could've been
done wayyyy sooner but I was really determined to get a good looking Art.java fractal



/******************************************************************************
 *  Describe your artistic creation and how you went about writing a program
 *  to produce it. If you found information about a fractal from somewhere
 *  (such as the course textbook, the booksite, or another offline or online
 *  source) cite it here.
 ******************************************************************************/
The artistic creation is inspired by a 3D cube fractal, where each cube is
representedby a hexagon that mimics the appearance of a 3D cube. The hexagons
are further embellished with internal hexagons to enhance the 3D effect.
The design is fractal-like, using mutual and conditional recursion to create a
complex pattern. Each hexagon is annotated with its recursion level, which also
dictates its color to some extent.

To be entirely honest, I really liked the example fractal drawn by Gerry Wan,
and I spent like at least 6-8hours trying to recreate his exactly because I
thought the design was so cool, but for the life of me I could not. So I ended
up settling for this, which i dont think is as cool as his was, but I think it
looks cool enough.

Weird request: but is it possible for me to see his code for the program? I
genuinely spent like 12-14hours trying to replicate his, I even got the hexagon
recursion down, and used a version of it in my own code, but I for the life of
me cannot get his colouring down 😭

if it's possible, im reallllly curious to see how he did it 🙏🏼


/******************************************************************************
 *  To get full credit on your Art.java, you need to have at least 3
 *  functions, including main(). Please list these functions below:
 ******************************************************************************/

 1. public static void main(String[] args)
 2. private static void drawSegmentedHexagon(double x, double y, double size,
                                                                int[][] colors)
 3. private static void draw3DCubeHexagon(double x, double y, double size, int n)
 4. private static void conditionalDraw(double x, double y, double size, int n)


/******************************************************************************
 *  To get full credit on your Art.java, you need to have used at least
 *  two of the criteria below. Please put an 'X' in the checkbox of the
 *   criteria that your submission fulfills, for instance:
 *     [ ] this is an unchecked criteria
 *     [X] this is a checked criteria
 ******************************************************************************/

    [ ] call one or more Transform2D method

    [ ] use different parameters than our examples: f(n, x, y, size)

    [X] use different StdDraw methods than in examples (e.g.,
        ellipses, arcs, text)

    [X] number of recursive calls not constant per level (e.g.,
        conditional recursion)

    [X] mutually recursive methods

    [X] multiple recursive methods

    [X] doesn't always recur from level n to level n-1

    [ ] draw between recursive calls, not just before/after all
        recursive calls

    [X] use recursive level for secondary purpose (e.g., level
        dictates color)


/******************************************************************************
 *  Please check the following box if you do not want your Art to be shared
 *  (anonymously) with the rest of the class.
 ******************************************************************************/

    [ ] I do *NOT* want my art to be shared with the class.


/******************************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 ******************************************************************************/

Yes or no?


/******************************************************************************
 *  Write any other comments here.
 ******************************************************************************/

