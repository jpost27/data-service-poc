import { SportPositions } from './SportPositions';
import { InjuryStatuses } from './InjuryStatuses';
import { CompetitorProviderIds } from './CompetitorProviderIds';
import { Teams } from './Teams';
import { CompetitorStatuses } from './CompetitorStatuses';
import { Leagues } from './Leagues';
export interface Competitors {
    competitorId: number;
    dateOfBirth?: Date;
    heightInInches?: number;
    leagueId: number;
    teamId?: number;
    weightInPounds?: number;
    college?: string;
    competitorStatusCode?: string;
    firstName: string;
    highSchool?: string;
    injuryStatusId?: string;
    lastName: string;
    uniformIdentifier?: string;
    teams?: Teams;
    competitorStatuses?: CompetitorStatuses;
    injuryStatuses?: InjuryStatuses;
    leagues?: Leagues;
    sportPositions?: SportPositions[];
    competitorProviderIds?: CompetitorProviderIds;
}
