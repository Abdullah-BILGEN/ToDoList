// rcc
import React, { Component } from 'react'

// I18N
import { withTranslation } from 'react-i18next';

// Footer Css
import './footer.css';

// CLASS COMPONENT
class Footer extends Component {

    // Component görünen ismi
    static displayName = "Blog_Footer";

    // Constructor
    constructor(props) {
        super(props);

        // STATE
        this.state = {}

        // BIND
    } //end constructor

    // CDM

    // FUNCTION

    //RENDER
    render() {

        //RETURN
        return (
            <React.Fragment>
               
                

            </React.Fragment>
        ) //end return
    } //end render
} //end class

// Higher Order Component
export default withTranslation()(Footer);