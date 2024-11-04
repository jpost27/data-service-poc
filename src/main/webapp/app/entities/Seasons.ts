import { SeasonTypes } from './SeasonTypes';
import { Events } from './Events';
import { Leagues } from './Leagues';
export interface Seasons {
    seasonId: number;
    endDate?: Date;
    leagueId: number;
    startDate?: Date;
    year: number;
    seasonTypeCode: string;
    leagues?: Leagues;
    seasonTypes?: SeasonTypes;
    events?: Events[];
}
