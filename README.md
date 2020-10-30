# Smart_Civics

An app that allows people to either use their gps location, or enter an address and receive a list of their current elected officials from the Google Civic API, a list of elections related
to their location (also the Civic API), and political news.  For Senate and Congress members, more data is available through the ProPublica APIs.  For Congress/Senate representatives, 
users can see a summary of their voting record, a list of recent votes (with detail), bills introduced, finances, and committees/subcommittees they are a part of. For any representative there is a screen where the representative in questions phone number (which they can call), job title, website (if available), and social media links (if available) are displayed. Not every 
representative has a photo resource provided by the Google API, so for members of Congress a separate API call is made to fetch a photograph, and for others a placeholder image is used instead.  

The election results (like the representative list result, can be retrieved with gps or an entered address) allow the user to view early polling sites, drop off sites, and voting sites
on a Google map, where they can be clicked for more information, and have directions generated to the location.  These results are from the Civic API.

The news component uses the News API to get stories related to general politics initially.  There is a menu option to change news results to news relevant to the Senate, Congress, or
general political news.  

-The architecture is MVVM.  

-There is a single activity with fragments connected to the view models.

-Image loading is done with Glide.

-API calls are made with Retrofit2.

-The app uses recycler views, with data binding on the items.

