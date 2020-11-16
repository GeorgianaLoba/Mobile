import React, {Component} from 'react';
import {TextInput, View, StyleSheet, TouchableOpacity, Text} from "react-native";
import BackgroundWrapper from './BackgroundWrapper';
import TopBar from './TopBar';


class EditScreen extends Component {
    constructor(props){
        super(props);
        this.state = {
            title: '',
            director: '',
            rating: '',
            review: '',
        }
    }

    render(){
        console.log(this.props.route.params.ex.title);
        return(
            <BackgroundWrapper>
                <TopBar text="Edit Your Movie Here"></TopBar>
                <View style={styles.form}>
                    <View style={styles.formElem}>
                        <Text style={styles.label}>Title</Text>
                        <TextInput 
                            style={styles.input}
                            placeholder={this.props.route.params.ex.title}
                            onChangeText={(text) => this.setState({title: text})}
                            >
                        </TextInput>
                    </View>
                    <View style={styles.formElem}>
                        <Text style={styles.label}>Director</Text>
                        <TextInput style={styles.input}
                            placeholder={this.props.route.params.ex.director}
                            onChangeText={(text) => this.setState({director: text})}
                            >                        
                        </TextInput>
                    </View>
                    <View style={styles.formElem}>
                        <Text style={styles.label}>Rating</Text>
                        <TextInput 
                            style={styles.input}
                            placeholder={String(this.props.route.params.ex.rating)}
                            onChangeText={(text) => this.setState({rating: text})}
                            >
                        </TextInput>
                    </View>
                    <View style={styles.formElem}>
                        <Text style={styles.label}>Review</Text>
                        <TextInput 
                            multiline={true}
                            style={styles.review}
                            placeholder={this.props.route.params.ex.review}
                            onChangeText={(text) => this.setState({review: text})}
                        >
                        </TextInput>
                    </View>

                </View>
                <TouchableOpacity style={styles.addButton} onPress={()=> this.handleEdit()}> 
                    <Text style={styles.addLabel}>Edit Movie</Text>
                </TouchableOpacity>
                <TouchableOpacity style={styles.returnButton} onPress={()=>this.props.navigation.goBack()}>
                    <Text style={styles.returnLabel}>Return</Text>
                </TouchableOpacity>
            </BackgroundWrapper>
        );
    }
    
    handleEdit() {
        const newId = this.props.route.params.ex.id;
        const movie = {
            id: newId,
            title: this.state.title,
            director: this.state.director,
            date: this.props.route.params.ex.date,
            review: this.state.review,
            rating: this.state.rating,
        }
        this.props.route.params.editMovie(movie);
        this.props.navigation.goBack();
    }
}

const styles = StyleSheet.create({
    form: {
        top: 100,
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        
    },
    formElem: {
        display: 'flex',
        flexDirection: 'row',
        margin: 15,
        left: 5,
    },
    label: {
        color: 'black', 
        fontSize: 20,
        lineHeight: 28,
        width: 100,
        height: 35,
        backgroundColor:'#219D1F',
        borderWidth: 2,
        borderColor:'#000000',
        textAlign: 'center',

    },
    input: {
        width: 250,
        height: 35,
        backgroundColor: '#fff',
        borderWidth: 1,
        borderColor: '#000',
        alignSelf: "flex-start",
    },
    review: {

        width: 250,
        height: 100,
        backgroundColor: '#fff',
        borderWidth: 1,
        borderColor: '#000',
        alignSelf: "flex-start",
    },
    addButton:{
        width: 180,
        height: 70,
        backgroundColor: '#219D1F',
        borderWidth: 5,
        borderColor: '#000',
        borderRadius: 15,
        left: 120,
        top: 125,
    },
    addLabel: {
        textAlign: 'center',
        top: 16,
        fontSize: 20,
        fontWeight: 'bold',
    },
    returnButton:{
        width: 100,
        height: 50,
        backgroundColor: '#219D1F',
        borderWidth: 5,
        borderColor: '#000',
        borderRadius: 15,
        left: 160,
        top: 120,
    },
    returnLabel: {
        textAlign: 'center',
        top: 10,
        fontSize: 16,
        fontWeight: 'bold',
    },
});

export default EditScreen;