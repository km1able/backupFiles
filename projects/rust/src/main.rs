//Import (via 'use') the 'fmt' module to make it available
use std::fmt; 
use std::mem; 
//define a structure which 'fmt::Display' will be implemented for. This is simply 
//a tuple struct containing an 'i32' bound to the name 'Structure'. 
struct Structure (i32); 

//In order to use the '{}' market, the train 'fmt::Display' must be implemented
//manually for the type

impl fmt::Display for Structure {
    //This trait requires 'fmt' with ths exact signature. 
    fn fmt (&self, f:&mut fmt::Formatter) -> fmt::Result {
        //write strictly the first element into the supplied output
        //stream: 'f'. Returns 'fmt::Result' which indicates whether 
        //the operation succeeded or failed. Note that 'write!' uses syntax 
        //which is very similar to 'printly!'
        write!(f, "{}", self.0)
    } //end fn: fmt
} //end imp: fmt::Display


#[derive(Debug)]
struct MinMax(i64, i64); 

//Implement 'Display' for 'MinMax'
impl fmt::Display for MinMax {
    fn fmt (&self, f: &mut fmt::Formatter) -> fmt::Result {
        //Use 'self.number' to refer to each positional data point. 
        write!(f, "({}, {})", self.0, self.1)
    } //end fn: fmt
} //end impl: Display


// Define a structure where the fields are nameable for comparison 
#[derive(Debug)]
struct Point2D {
    x: f64, 
    y: f64, 
}

//Similarly, implement for POint2D
impl fmt::Display for Point2D {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        //Customize so only 'x' and 'y' are denoted
        write!(f, "x: {}, y: {}", self.x, self.y)
    }
}

// Define a structure named `List` containing a `Vec`.
struct List(Vec<i32>);

impl fmt::Display for List {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        // Extract the value using tuple indexing
        // and create a reference to `vec`.
        let vec = &self.0;

        write!(f, "[")?;

        // Iterate over `vec` in `v` while enumerating the iteration
        // count in `count`.
        for (count, v) in vec.iter().enumerate() {
            // For every element except the first, add a comma.
            // Use the ? operator, or try!, to return on errors.
            if count != 0 { write!(f, ", ")?; }
            write!(f, "{}", v)?;
        }

        // Close the opened bracket and return a fmt::Result value
        write!(f, "]")
    }
}

//Tuples can be used as function arguments and as return values
fn reverse (pair: (i32, bool)) -> (bool, i32) {
    //'let' can be used to bind the members of a tuple to variables
    let (integer, boolean) = pair; 
    (boolean, integer)
}//end fn: reverse


/* Arrays and slices 
*/
//This function borrows a slice
fn analyze_slice (slice: &[i32]) {
    println!("First element of the slice: {}", slice[0]); 
    println!("The slice has {} elements", slice.len()); 
}


//Create an 'enum' to classify a web event. Note how both names and type
//information together specify the variant: 
//'PageLoad != PageUnload' and 'KeyPRess(char) != Paste(String)'. 
//Each is different and independent
enum WebEvent {
    //An 'enum' may either be 'unit-like', 
    PageLoad, 
    PageUnload, 
    //like tuple structs
    KeyPress(char), 
    Paste(String), 
    //or like structures
    Click {x:i64, y:i64}, 
}

//A function which takes a 'WebEvent' enum as an argument and returns nothing
fn inspect(event: WebEvent) {
    match event {
        WebEvent::PageLoad => println!("page loaded"),
        WebEvent::PageUnload => println!("page unloaded"), 
        //Destructure 'c' from inside the 'enum' 
        WebEvent::KeyPress(c) => println!("pressed '{}'.", c), 
        WebEvent::Paste(s) => println!("pasted \"{}\".",s), 
        //Destructure 'Click' into 'x' and 'y'
        WebEvent::Click {x, y} => {
            println!("clicked at x={}, y={}.", x, y); 
        }, //end webevent click
    } //end match: event
}//end fn: inspect(WebEvent)


enum Status {
    Rich,
    Poor, 
}

enum Work {
    Civilian, 
    Soldier, 
}
fn main() {

    /*Manual/Automatic scoping using 'use' */
    //Explicitly 'use' each name so they are available without manual scoping
    use Status::{Poor, Rich}; 
    //Automatically 'use' each name inside 'Work'
    use Work::*; 
    //Equivalent to 'Status::Poor'
    let status = Poor;
    //Equivalent to 'Work::Civilian'
    let work = Civilian; 

    match status {
        Rich => println!("The rich have lots of money!"), 
        Poor => println!("The poor have no money..."),
    }

    match work {
        //Note the lack of scoping 
        Civilian => println!("Civilians work!"), 
        Soldier => println!("Soldiers fight"), 
    }

    let pressed = WebEvent::KeyPress('x'); 
    //'to_owned()' creates an owned 'String' from a string slice
    let pasted = WebEvent::Paste("my text".to_owned()); 
    let click = WebEvent::Click{x:20, y:80}; 
    let load = WebEvent::PageLoad; 
    let unload = WebEvent::PageUnload; 

    inspect (pressed); 
    inspect(pasted); 
    inspect(click); 
    inspect(load); 
    inspect(unload); 
    //all elements can be initialzied to the same value; 
    let ys: [i32; 500] = [0; 500]; 
    //arrays are stack allocated
    println!("array occupies {} bytes",  mem::size_of_val(&ys)); 
    //arrays can be automatically borrowed as slices
    println!("borrow the whole array as a slice"); 
    analyze_slice(&ys); 
    //slices can point to a section of the aray 
    println!("borrow a section of the array a slice"); 
    analyze_slice(&ys[1 .. 4]); 

    //tuples can be tuple members
    let tuple_of_tuples = ((1u8, 2u16, 2u32), (4u64, -1i8), -2i16); 
    //tuples are printable 
    println!("tuple of tuples: {:?}", tuple_of_tuples); 

    let pair = (1, true); 
    println!("The reversed pair is: {:?}", reverse(pair)); 

    let v = List(vec![1,2,3]); 
    println!("{}", v); 
    let minmax = MinMax(0, 14); 

    println!("Compare structures:"); 
    println!("Display: {}", minmax); 
    println!("Debug: {:?}", minmax); 

    let big_range = MinMax(-300, 300); 
    let small_range = MinMax(-3, 3); 

    println!("The big range is {big} and the small is {small}", 
    small = small_range, big = big_range); 

    let point = Point2D {x:3.3, y: 7.2}; 

    println!("Compare Points:"); 
    println!("Display: {}", point); 
    println!("Debug: {:?}", point); 

    //Error. Both 'Debug' and 'Display' were implemented but '{:b}'
    //requires 'fmt::Binary' to be implemented. this will not work. 
    //println!("What does Point2D look like in binary: {:b}?", point); 

}
