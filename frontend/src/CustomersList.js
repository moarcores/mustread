import React, {Component} from 'react';
import ProfilesService from './ProfilesService';

const profilesService = new ProfilesService();
class ProfilesList extends Component {
    constructor(props) {
        super(props);
        this.state  = {
            customers: [],
            nextPageURL: ''
        };
        this.nextPage = this.nextPage.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
    }

    componentDidMount() {
        const self = this;
        profilesService.getProfiles().then(function (result) {
            self.setState({ customers:  result.data, nextPageURL:  result.nextlink})
        });
    }

    handleDelete(e,pk){
        const self = this;
        profilesService.deleteProfile({pk: pk}).then(()=>{
            const newArr = self.state.customers.filter(function (obj) {
                return obj.pk !== pk;
            });
            self.setState({customers: newArr})
        });
    }
}
export default ProfilesList;