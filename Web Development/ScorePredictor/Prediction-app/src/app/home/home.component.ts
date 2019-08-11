import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  isLoggedIn = true;
  imgLoc = 'assets/Images/';
  todaysMatches = [
    {
      sport: 'football',
      league: 'Primier League',
      home: 'Manchester United',
      away: 'Chelsea',
      date: '11/8/2019',
      time: '21:00',
      homeLogo: this.imgLoc + 'manchester-united.png',
      awayLogo: this.imgLoc + 'chelsea.png',
      stadium: 'Old Trafford, Manchester'
    },
    {
      sport: 'cricket',
      league: 'International',
      home: 'West-Indies',
      away: 'India',
      date: '11/8/2019',
      time: '18:00',
      homeLogo: this.imgLoc + 'windies.png',
      awayLogo: this.imgLoc + 'india.png',
      stadium: 'Port of Spain, Trinidad & Tobago'
    },
    {
      sport: 'cricket',
      league: 'Indian Premier League',
      home: 'Mumbai Indians',
      away: 'Chennai Super Kings',
      date: '12/8/2019',
      time: '09:00',
      homeLogo: this.imgLoc + 'mumbai-indians.png',
      awayLogo: this.imgLoc + 'chennai-super-kings.png',
      stadium: 'Wankhede Stadium, Mumbai'
    },
    {
      sport: 'football',
      league: 'Laliga',
      home: 'FC Barcelona',
      away: 'Real Madrid',
      date: '12/8/2019',
      time: '16:00',
      homeLogo: this.imgLoc + 'barcelona.png',
      awayLogo: this.imgLoc + 'real-madrid.png',
      stadium: 'Camp Nou, Barcelona'
    },
    {
      sport: 'football',
      league: 'Primier League',
      home: 'Tottenham Hotspur',
      away: 'Arsenal',
      date: '12/8/2019',
      time: '16:30',
      homeLogo: this.imgLoc + 'tottenham.png',
      awayLogo: this.imgLoc + 'arsenal.png',
      stadium: 'New White Hart Lane, London'
    },
    {
      sport: 'cricket',
      league: 'International',
      home: 'England',
      away: 'Australia',
      date: '12/8/2019',
      time: '17:30',
      homeLogo: this.imgLoc + 'england.png',
      awayLogo: this.imgLoc + 'australia.png',
      stadium: 'Lord\'s Cricket Ground, London'
    }
  ];
  match = this.todaysMatches[5];

  ngOnInit() {
  }

}
