import { TeamAssociations } from './TeamAssociations';
import { Venues } from './Venues';
import { TeamProviderIds } from './TeamProviderIds';
import { Competitors } from './Competitors';
import { TeamColors } from './TeamColors';
import { Leagues } from './Leagues';
export interface Teams {
    teamId: number;
    leagueId: number;
    primaryVenueId?: number;
    yearFounded?: number;
    abbreviation?: string;
    market?: string;
    name?: string;
    owner?: string;
    venues?: Venues;
    leagues?: Leagues;
    teamProviderIds?: TeamProviderIds;
    competitors?: Competitors[];
    teamColors?: TeamColors[];
    teamAssociations?: TeamAssociations[];
}
