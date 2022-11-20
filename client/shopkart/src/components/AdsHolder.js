import React, { Component } from "react";
import axios from "axios";
import {connect} from "react-redux"
import { getAds } from "../actions/ads";

export default class AdsHolder extends Component {
    // constructor(props) {
    //     super(props);

    //     this.state = {
    //         ads: "default ads",
    //     };
    // }
    // componentWillMount() {
    //     axios.get("http://localhost:8080/ads")
    //         .then((response) => {
    //             this.setState({ads: response.data})
    //         })
    //         .catch((error) => {
    //                 console.log(error);
    //             }
    //         )
    // }

    componentWillMount(){
        this.props.getAds()
    }

    render() {
        return (
            <div>
                 
                     <p>
                         {this.props.ads.map(ads =>(
                            <div>
                            <div className={"ads"}>
                            {ads.content}
                            </div>
                            </div>
                         ))}
                     </p>
             </div>
        );
    }
}