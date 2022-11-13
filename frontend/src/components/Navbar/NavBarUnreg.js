import './navbar.css';
import React, { Fragment } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

// import { default as UnregisteredHomePage } from "../../pages";

export default function NavBarUnreg() {
  return (
    <div className="Navbar">
      <Router> {/* refer to: https://www.freecodecamp.org/news/a-complete-beginners-guide-to-react-router-include-router-hooks/#using-links-to-switch-pages */}
        <main>
          <nav class="Navbar">
            <ul>
                          <li><Link to='..//pages/Unregistered-User-Page/unregistered-user-page.js'>Explore</Link></li>
                          <li><Link to='..//pages/SearchPage/search-page.js'>Search</Link></li>
                          <li><Link to='..//pages/AccountPage/accountpage.js'>Account</Link></li>
            </ul>
          </nav>

          <Routes> 
            <Route path="..//pages/unregistered-user-page.js" exact component={UnregisteredHomePage}/>
            <Route render={() => <h1>404: PAGE NOT FOUND</h1>}/>
          </Routes>

        </main>
      </Router>
    </div>

  );
}

const UnregisteredHomePage = () => {
  <Fragment>
    <h3>Explore</h3>
      {/* <FakeText /> */}
      <h3>Search</h3>
      { }
  </Fragment>
}



