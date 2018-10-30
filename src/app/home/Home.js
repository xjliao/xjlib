import React from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import WrappedComponent from '../common/WrappedComponent';
import PropTypes from 'prop-types';

// Home = (data) => {
// 	return (
// 		<View>
// 			<Text>home.Version: 1.0.0 {this.props.data}</Text>
// 			<TouchableOpacity>
// 				<Text>Check for updates</Text>
// 			</TouchableOpacity>
// 		</View>
// 	);
// };

class Home extends React.Component {
	render() {
		return (
			<View>
				<Text>home.Version: 1.0.0 {this.props.data}</Text>
				<TouchableOpacity>
					<Text>Check for updates</Text>
				</TouchableOpacity>
			</View>
		);
	}
}

Home = WrappedComponent(Home, '廖新建');

export default Home;
