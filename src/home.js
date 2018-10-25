import React from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import codePush from 'react-native-code-push';
import { getBorrowList } from './net/api';

let codePushOptions = {
	checkFrequency: codePush.CheckFrequency.ON_APP_RESUME,
	updateDialog: true,
	installMode: codePush.InstallMode.IMMEDIATE
};

class Home extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			datas: []
		};
	}

	onButtonPress() {
		codePush.sync({
			updateDialog: true,
			installMode: codePush.InstallMode.IMMEDIATE
		});
	}

	getBorrowListSuccess = (data) => {
		console.log('getBorrowListSuccess');

		this.setState({
			datas: data
		});
	};

	getBorrowListFailure = (error) => {
		console.log('getBorrowListFailure');
		console.log(error);
	};

	componentDidMount() {
		var params = {
			status: '0',
			order_type: '1',
			current_page: '1',
			page_size: '100',
			borrow_type: '0',
			time_limit: '0',
			apr: '0',
			type: '0',
			platform: 'android'
		};

		getBorrowList(params, this.getBorrowListSuccess, this.getBorrowListFailure);
	}

	codePushStatusDidChange(status) {
		switch (status) {
			case codePush.SyncStatus.CHECKING_FOR_UPDATE:
				console.log('Checking for updates.');
				break;
			case codePush.SyncStatus.DOWNLOADING_PACKAGE:
				console.log('Downloading package.');
				break;
			case codePush.SyncStatus.INSTALLING_UPDATE:
				console.log('Installing update.');
				break;
			case codePush.SyncStatus.UP_TO_DATE:
				console.log('Up-to-date.');
				break;
			case codePush.SyncStatus.UPDATE_INSTALLED:
				console.log('Update installed.');
				break;
		}
	}

	codePushDownloadDidProgress(progress) {
		console.log(progress.receivedBytes + ' of ' + progress.totalBytes + ' received.');
	}

	render() {
		return (
			<View>
				<Text>home.Version: 1.0.0 {this.state.datas}</Text>
				<TouchableOpacity onPress={this.onButtonPress}>
					<Text>Check for updates</Text>
				</TouchableOpacity>
			</View>
		);
	}
}
var styles = StyleSheet.create({
	container: {
		flex: 1,
		justifyContent: 'center'
	},
	hello: {
		fontSize: 20,
		textAlign: 'center',
		margin: 10
	}
});

Home = codePush(codePushOptions)(Home);

export default Home;
