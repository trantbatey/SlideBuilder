document.write(`
    <div class="sidenav">
        <label><strong>Table of contents</strong></label>
        <ul class="list-unstyled">
            <img src="resource/images/HTMLandCSS02.jpg" alt="First HTML CSS Image" class="md-img">
            </img>
            <img src="resource/images/HTMLandCSS02.jpg" alt="Second HTML CSS Image" class="md-img">
            </img>
            <img src="resource/images/HTMLandCSS03.jpg" alt="Third HTML CSS Image" class="md-img">
            </img>
            <br>
            </br>
            <h1>
                CSS II
            </h1>
            In this section, we will be covering how to use CSS to create responsive layouts. We will cover how to do this with vanilla CSS, then talk about the Bootstrap Framework.
            <br>
            </br>
            As we go through this section, the following definitions will be useful:
            <br>
            </br>
            <ul>
                <li>
                    <a href="./null.html" title="null">
                        null
                    </a>
                    <b>
                        fixed layout:
                    </b>
                    the layout is specified in hardcoded pixel values
                </li>
                <li>
                    <a href="./null.html" title="null">
                        null
                    </a>
                    <b>
                        fluid layout:
                    </b>
                    the layout is specified in percentages, but does not change based on screen size
                </li>
                <li>
                    <a href="./null.html" title="null">
                        null
                    </a>
                    <b>
                        responsive layout:
                    </b>
                    the layout uses media queries to change based on screen size
                </li>
                    <b>
                <h1 id="media_queries">
                    Media Queries
                </h1>
                <bullet>
                    What are Media Queries?
                </bullet>
                <bullet>
                    Media queries are a feature of CSS3 allowing content rendering to adapt to different conditions such as screen resolution.
                </bullet>
                <bullet>
                    What is CSS3?
                </bullet>
                <bullet>
                    CSS3 is the latest evolution of the Cascading Style Sheets language and aims at extending CSS2. It brings a lot of long-awaited novelties, like rounded corners, shadows, gradients, transitions or
                    animations, as well as new layouts like multi-columns, flexible box or grid layouts.
                </bullet>
                <bullet>
                    What are Media Queries primarily used to do?
                </bullet>
                <bullet>
                    Media Queries are primarily used to accomplish
                    <b>
                        Responsive Web Design
                    </b>
                    .
                </bullet>
                <h1 id="responsive_web_design">
                    Responsive Web Design
                </h1>
                <bullet>
                    What is Responsive Web Design?
                </bullet>
                <bullet>
                    Responsive web design is an approach to web design that makes web pages render well on a variety of devices and window or screen sizes.
                </bullet>
                <bullet>
                    More Information and Demo: Responsive Web Design - Media Queries
                    <a href="https://www.w3schools.com/css/css_rwd_mediaqueries.asp">
                        https://www.w3schools.com/css/css_rwd_mediaqueries.asp
                    </a>
                    Take note of the meta tag named
                    <b>
                        "viewport"
                    </b>
                    !
                </bullet>
                <h1 id="viewport">
                    Viewport
                </h1>
                <bullet>
                    What is The Viewport?
                </bullet>
                <bullet>
                    The viewport is the user's visible area of a web page.
                </bullet>
                <bullet>
                    HTML5 introduced a method to let web designers take control over the viewport, through the &lt;meta> tag.
                </bullet>
                <bullet>
                    <b>
                        EXAMPLE:
                    </b>
                    <text>
                        &lt;meta name="viewport" content="width=device-width, initial-scale=1.0">
                    </text>
                </bullet>
                <bullet>
                    Where is the viewport meta tag place?
                </bullet>
                <bullet>
                    In the html file, in the &lt;head> section.
                </bullet>
                <bullet>
                    What are the viewport options?
                </bullet>
                <bullet>
                    <ul>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            The width=device-width part sets the width of the page to follow the screen-width of the device (which will vary depending on the device).
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            The initial-scale=1.0 part sets the initial zoom level when the page is first loaded by the browser.
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                    </bullet>
                    <bullet>
                        For other options:
                        <a href="https://responsivedesign.is/develop/responsive-html/viewport-meta-element/">
                            https://responsivedesign.is/develop/responsive-html/viewport-meta-element/
                        </a>
                    </bullet>
                    <h2 id="mini_lab_01">
                        MINI LAB 01
                    </h2>
                    <ul>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            What are Media Queries?
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            What is CSS3?
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            What are Media Queries primarily used to do?
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            What is Responsive Web Design?
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            What is The Viewport?
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            Where is the viewport meta tag placed?
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                            Which viewport options will we be using?
                        </li>
                        <li>
                            <a href="./null.html" title="null">
                                null
                            </a>
                        <h1 id="media_rule">
                            The @media rule
                        </h1>
                        <bullet>
                            Once the viewport is set, how is the Media Query invoked?
                        </bullet>
                        <bullet>
                            <b>
                                EXAMPLE:
                            </b>
                            <br>
                            </br>
                            <text tid="t1">
                            </text>
                        </bullet>
                        <bullet>
                            <b>
                                CSS Syntax:
                            </b>
                            <br>
                            </br>
                            <text tid="t2">
                            </text>
                        </bullet>
                        <bullet>
                            What are the options for mediatypes and mediafeatures?
                        </bullet>
                        <bullet>
                            More information on mediatypes and mediafeatures:
                            <a href="https://www.w3schools.com/cssref/css3_pr_mediaquery.asp">
                                https://www.w3schools.com/cssref/css3_pr_mediaquery.asp
                            </a>
                        </bullet>
                        <h1 id="breakpoints">
                            Breakpoints
                        </h1>
                        <bullet>
                            What are Breakpoints?
                        </bullet>
                        <bullet>
                            CSS breakpoints are points where the website content responds according to the device width, allowing you to show the best possible layout to the user. They are also called media query breakpoints, as they are
                            used with media query.
                        </bullet>
                        <bullet>
                            <img src="resource/images/css-breakpoints-layouts-01.jpg" alt="Third HTML CSS Image" class="lg-img">
                            </img>
                        </bullet>
                        <bullet>
                            They are called breakpoints because traditionally major layout elements are broken and repositioned at these widths. Although this did not always happen, the name stuck.
                        </bullet>
                        <bullet>
                            The breakpoints are set by the as a media feature in the @media rule.
                        </bullet>
                        <bullet>
                            <b>
                                EXAMPLES:
                            </b>
                            <br>
                            </br>
                            <text>
                                @media screen and (min-width:576px) { /* ... */ }
                            </text>
                            In this rule, a breakpoint is set at the width of 576px. Any time the screen width drops below 576px, this rule would no longer apply.
                            <text>
                                @media screen and (min-width:960px) and (max-width:1200px) { /* ... */ }
                            </text>
                            This rule applies to any width greater than 960px and less than 1200px.
                        </bullet>
                        <bullet>
                            <b>
                                Some common breakpoints:
                            </b>
                            <br>
                            </br>
                            <text tid="t3">
                            </text>
                        </bullet>
                        <h1 id="mobile_first_approach">
                            Mobile First Approach
                        </h1>
                        <bullet>
                            What is Mobile First Approach?
                        </bullet>
                        <bullet>
                            A mobile first approach to writing CSS means writing your default styling for a mobile device, then using media queries to change the layout based on larger devices. This approach works well because you will
                            lways support mobile and can build up from there. Working larger first usually introduces complications trying to break a design down to fit.
                        </bullet>
                        <h2 id="mini_lab_02">
                            MINI LAB 02
                        </h2>
                        <ul>
                            <li>
                                <a href="./null.html" title="null">
                                    null
                                </a>
                                What are the four mediatypes?
                            </li>
                            <li>
                                <a href="./null.html" title="null">
                                    null
                                </a>
                                What are two mediafeatures?
                            </li>
                            <li>
                                <a href="./null.html" title="null">
                                    null
                                </a>
                                What are Breakpoint?
                            </li>
                            <li>
                                <a href="./null.html" title="null">
                                    null
                                </a>
                                What is Mobile First Approach?
                            </li>
                            <li>
                                <a href="./null.html" title="null">
                                    null
                                </a>
                                Style the html code below so that the narrow div displays at page widths 600px or less and the wide div displays at page widths 600px and above.
                                <br>
                                </br>
                                <text tid="t4">
                                </text>
                            </li>
                                <text tid="t4">
                            <bullet>
                                What are Media Queries?
                            </bullet>
                            <bullet indent="indent-star">
                                Media queries are a feature of
                                <b>
                                    CSS3
                                </b>
                                allowing content rendering to adapt to different conditions such as screen resolution.
                            </bullet>
                            <bullet>
                                What is CSS3?
                            </bullet>
                            <bullet indent="indent-star">
                                CSS3 is the latest evolution of the Cascading Style Sheets language and aims at extending CSS2.
                            </bullet>
                            <bullet>
                                What are Media Queries primarily used to do?
                            </bullet>
                            <bullet indent="indent-star">
                                Media Queries are primarily used to accomplish Responsive Web Design.
                            </bullet>
                            <bullet>
                                What is Responsive Web Design?
                            </bullet>
                            <bullet indent="indent-star">
                                Responsive web design is an approach to web design that makes web pages render well on a variety of devices
                                and window or screen sizes.
                            </bullet>
                            <bullet>
                                What is The Viewport?
                            </bullet>
                            <bullet indent="indent-star">
                                The viewport is the user's visible area of a web page.
                            </bullet>
                            <bullet>
                                Where is the viewport meta tag placed?
                            </bullet>
                            <bullet indent="indent-star">
                                In the html file, in the &lt;head> section.
                            </bullet>
                            <bullet>
                                Which viewport options will we be using?
                            </bullet>
                            <bullet indent="indent-star">
                                width=device-width and initial-scale=1.0
                            </bullet>
                            <bullet>
                                What are the four mediatypes?
                            </bullet>
                            <bullet indent="indent-star">
                                all, print, screen, speech
                            </bullet>
                            <bullet>
                                What are two mediafeatures?
                            </bullet>
                            <bullet indent="indent-star">
                                min-width, max-width
                            </bullet>
                            <bullet>
                                What are Breakpoints?
                            </bullet>
                            <bullet indent="indent-star">
                                CSS breakpoints are points where the website content responds according to the device width, allowing you to show the best possible layout to the user.
                            </bullet>
                            <bullet>
                                Why is the Mobile First Approach a good choice?
                            </bullet>
                            <bullet indent="indent-star">
                                Because scaling down a larger design introduces complications trying to break a design down to fit.
                            </bullet>
                            <br>
                            </br>
                            <a Target="blank" href="https://java.codeup.com/html-css/css-i/selectors-and-properties/#further-reading" class="btn btn-info">
                                Further Reading and Exercises
                            </a>
        </ul>
    </div>
`);
