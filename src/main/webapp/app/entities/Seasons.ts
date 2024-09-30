import { SeasonTypes } from './SeasonTypes';
import { Leagues } from './Leagues';
export interface Seasons {
    leagueId: number;
    seasonTypeCode: string;
    year: number;
    endDate?: Date;
    startDate?: Date;
    leagues?: Leagues;
    seasonTypes?: SeasonTypes;
}
