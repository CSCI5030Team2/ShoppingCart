import { GET_ADS } from "../actions/types";

const initialstate = {
    ads:""
}

export default function(state=initialstate,action){
    switch (action.type){
        case GET_ADS:
            return{...state,ads: action.payload};
        default:
            return state;
    }
}