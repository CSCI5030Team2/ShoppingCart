import React, { Component } from "react";
import axios from "axios";

export default class AdsHolder extends Component {
    constructor(props) {
        super(props);

        this.state = {
            ads: "default ads",
        };
    }
    // This function is used to call the random ads stored in the database that are displayed on the screen 
    componentWillMount() {
        axios.get("http://localhost:8080/ads")
            .then((response) => {
                this.setState({ads: response.data})
            })
            .catch((error) => {
                    console.log(error);
                }
            )
    }

    render() {
        return (
            <div>
                 <div className={"ads"}>
                     <p>
                         {this.state.ads}
                     </p>
                 </div>
             </div>
        );
    }
}